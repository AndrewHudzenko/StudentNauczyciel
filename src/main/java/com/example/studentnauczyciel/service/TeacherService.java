package com.example.studentnauczyciel.service;

import java.util.List;
import java.util.Set;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.data.domain.PageRequest;

public interface TeacherService {
    Teacher save(Teacher teacher);

    Teacher findById(Long id);

    void delete(Long id);

    List<Teacher> findAll(PageRequest pageRequest);

    Teacher findByFirstNameAndLastName(String firstName, String lastName);

    List<Teacher> findAllByStudentId(Long studentId);

    Set<Student> addStudent(Long teacherId, Long studentId);

    void removeStudent(Long teacherId, Long studentId);
}
