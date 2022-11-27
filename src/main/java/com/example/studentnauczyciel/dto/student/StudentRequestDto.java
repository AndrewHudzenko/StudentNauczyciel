package com.example.studentnauczyciel.dto.student;

import com.example.studentnauczyciel.lib.ValidEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequestDto {
    @NotNull(message = "First name can not be null")
    @Size(min = 3, max = 30, message = "First name length must be between 3 and 30")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @Size(min = 3, max = 30, message = "Last name length must be between 3 and 30")
    private String lastName;

    @ValidEmail
    private String email;

    @NotNull(message = "Direction can not be null")
    @Size(min = 3, max = 30, message = "Direction length must be between 3 and 30")
    private String direction;
}
