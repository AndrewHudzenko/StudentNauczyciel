package com.example.studentnauczyciel.dto.student;

import lombok.Data;

@Data
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String direction;
}
