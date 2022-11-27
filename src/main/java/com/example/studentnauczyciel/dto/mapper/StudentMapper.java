package com.example.studentnauczyciel.dto.mapper;

import com.example.studentnauczyciel.dto.student.StudentRequestDto;
import com.example.studentnauczyciel.dto.student.StudentResponseDto;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapper implements ResponseMapper<StudentResponseDto, Student>,
        RequestMapper<StudentRequestDto, Student> {

    @Override
    public Student toModel(StudentRequestDto requestDto) {
        Student student = new Student();
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());
        student.setAge(requestDto.getAge());
        student.setDirection(requestDto.getDirection());
        student.setEmail(requestDto.getEmail());
        return student;
    }

    @Override
    public StudentResponseDto toDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setDirection(student.getDirection());
        Set<Long> teachersIds = student.getTeachers()
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toSet());
        studentResponseDto.setTeachersIds(teachersIds);
        return studentResponseDto;
    }
}
