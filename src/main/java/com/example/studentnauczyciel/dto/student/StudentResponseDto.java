package com.example.studentnauczyciel.dto.student;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String direction;
    private Set<Long> teachersIds;
}
