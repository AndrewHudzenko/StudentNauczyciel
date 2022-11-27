package com.example.studentnauczyciel.dto.student;

import com.example.studentnauczyciel.lib.ValidEmail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class StudentRequestDto {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Min(value = 18)
    private Integer age;

    @ValidEmail
    private String email;

    @NotNull(message = "Direction can not be null")
    private String direction;
    private Set<Long> teachersIds;
}
