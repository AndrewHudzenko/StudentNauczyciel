package com.example.studentnauczyciel.service;

import java.util.List;
import java.util.Set;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.data.domain.PageRequest;

public interface StudentService {
    Student save(Student student);

    Student findById(Long id);

    void delete(Long id);

    List<Student> findAll(PageRequest pageRequest);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findAllByTeacherId(Long teacherId);

    Set<Teacher> addTeacher(Long studentId, Long teacherId);
}
