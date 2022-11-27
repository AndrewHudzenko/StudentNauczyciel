package com.example.studentnauczyciel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String direction;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "students")
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getStudents().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id)
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(age, student.age)
                && Objects.equals(email, student.email)
                && Objects.equals(direction, student.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, direction);
    }

    public void removeTeacher(long teacherId) {
        Teacher teacher = this.teachers.stream()
                .filter(t -> t.getId() == teacherId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student doesn't have teacher with Id: "
                        + teacherId));
        this.teachers.remove(teacher);
        teacher.getStudents().remove(this);
    }

    @PreRemove
    private void removeTeachersFromStudents() {
        for (Teacher teacher : teachers) {
            teacher.getStudents().remove(this);
        }
        this.teachers.clear();
    }
}
