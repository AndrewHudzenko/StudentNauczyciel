package com.example.studentnauczyciel.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import com.example.studentnauczyciel.repository.StudentRepository;
import com.example.studentnauczyciel.repository.TeacherRepository;
import com.example.studentnauczyciel.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find a student by Id: " + id));
    }

    @Override
    public Set<Student> findAll(PageRequest pageRequest) {
        return studentRepository.findAll(pageRequest).stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findStudentByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(
                        () -> new RuntimeException("Can't find a student by first name: "
                                + firstName + " and last name: " + lastName));
    }

    @Override
    public Set<Student> findAllByTeacherId(Long teacherId/*, PageRequest pageRequest*/) {
        return studentRepository.findStudentsByTeachersId(teacherId);
    }

    @Override
    public Set<Teacher> addTeacher(Long studentId, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(
                        () -> new RuntimeException("Can't find a teacher by Id: "
                                + teacherId));
        Student student = findById(studentId);
        student.addTeacher(teacher);
        studentRepository.save(student);
        return student.getTeachers();
    }
}
