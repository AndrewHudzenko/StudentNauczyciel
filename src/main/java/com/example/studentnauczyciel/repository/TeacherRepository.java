package com.example.studentnauczyciel.repository;

import java.util.List;
import java.util.Optional;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,
                                                                     String lastName);

    List<Teacher> findTeachersByStudentsId(Long studentId);
}
