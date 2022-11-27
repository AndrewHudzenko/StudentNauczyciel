package com.example.studentnauczyciel.dto.teacher;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String subject;
    private Set<Long> studentsIds;
}
