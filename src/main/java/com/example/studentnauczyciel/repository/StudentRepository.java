package com.example.studentnauczyciel.repository;

import java.util.Optional;
import java.util.Set;

import com.example.studentnauczyciel.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByFirstNameAndLastName(String firstName,
                                                        String lastName);

    Set<Student> findStudentsByTeachersId(Long teacherId);
}
