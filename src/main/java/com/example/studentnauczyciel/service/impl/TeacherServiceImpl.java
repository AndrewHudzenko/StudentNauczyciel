package com.example.studentnauczyciel.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import com.example.studentnauczyciel.repository.StudentRepository;
import com.example.studentnauczyciel.repository.TeacherRepository;
import com.example.studentnauczyciel.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find a teacher by Id: " + id));
    }

    @Override
    public List<Teacher> findAll(PageRequest pageRequest) {
        return teacherRepository.findAll(pageRequest).stream().collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher findByFirstNameAndLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(
                        () -> new RuntimeException("Can't find a teacher by first name: "
                                + firstName + " and last name: " + lastName));
    }

    @Override
    public List<Teacher> findAllByStudentId(Long studentId) {
        return teacherRepository.findTeachersByStudentsId(studentId);
    }

    @Override
    public Set<Student> addStudent(Long teacherId, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new RuntimeException("Can't find a student by Id: "
                                + studentId));
        Teacher teacher = findById(teacherId);
        teacher.addStudent(student);
        teacherRepository.save(teacher);
        return teacher.getStudents();
    }

    @Override
    public void removeStudent(Long teacherId, Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new RuntimeException("Can't find a student by Id: "
                                + studentId));
        Teacher teacher = findById(teacherId);
        teacher.removeStudent(studentId);
        teacherRepository.save(teacher);
    }
}
