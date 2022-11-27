package com.example.studentnauczyciel.repository;

import java.util.Optional;
import java.util.Set;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByFirstNameAndLastName(String firstName,
                                                        String lastName);

    Set<Teacher> findTeachersByStudentsId(Long studentId);
}
