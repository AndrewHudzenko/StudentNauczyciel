package com.example.studentnauczyciel.dto.mapper;

import com.example.studentnauczyciel.dto.teacher.TeacherRequestDto;
import com.example.studentnauczyciel.dto.teacher.TeacherResponseDto;
import com.example.studentnauczyciel.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements ResponseMapper<TeacherResponseDto, Teacher>,
        RequestMapper<TeacherRequestDto, Teacher> {

    @Override
    public Teacher toModel(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherRequestDto.getFirstName());
        teacher.setLastName(teacherRequestDto.getLastName());
        teacher.setAge(teacherRequestDto.getAge());
        teacher.setEmail(teacherRequestDto.getEmail());
        teacher.setSubject(teacherRequestDto.getSubject());
        return teacher;
    }

    @Override
    public TeacherResponseDto toDto(Teacher teacher) {
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setId(teacher.getId());
        teacherResponseDto.setFirstName(teacher.getFirstName());
        teacherResponseDto.setLastName(teacher.getLastName());
        teacherResponseDto.setAge(teacher.getAge());
        teacherResponseDto.setEmail(teacher.getEmail());
        teacherResponseDto.setSubject(teacher.getSubject());
        return teacherResponseDto;
    }
}
