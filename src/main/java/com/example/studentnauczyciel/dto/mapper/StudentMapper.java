package com.example.studentnauczyciel.dto.mapper;

import com.example.studentnauczyciel.dto.student.StudentRequestDto;
import com.example.studentnauczyciel.dto.student.StudentResponseDto;
import com.example.studentnauczyciel.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements ResponseMapper<StudentResponseDto, Student>,
        RequestMapper<StudentRequestDto, Student> {

    @Override
    public Student toModel(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDirection(studentRequestDto.getDirection());
        return student;
    }

    @Override
    public StudentResponseDto toDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setDirection(student.getDirection());
        return studentResponseDto;
    }
}
