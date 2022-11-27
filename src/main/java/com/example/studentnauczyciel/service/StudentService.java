package com.example.studentnauczyciel.service;

import java.util.Set;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Student save(Student student);

    Student findById(Long id);

    void deleteById(Long id);

    Set<Student> findAll(PageRequest pageRequest);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    Set<Student> findAllByTeacherId(Long teacherId);

    Set<Teacher> addTeacher(Long studentId, Long teacherId);
}
