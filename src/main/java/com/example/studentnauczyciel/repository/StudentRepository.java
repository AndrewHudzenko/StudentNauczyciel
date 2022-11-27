package com.example.studentnauczyciel.repository;

import java.util.List;
import java.util.Optional;
import com.example.studentnauczyciel.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,
                                                                     String lastName);

    List<Student> findStudentsByTeachersId(Long teacherId);
}
