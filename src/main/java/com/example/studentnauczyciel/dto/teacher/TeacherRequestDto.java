package com.example.studentnauczyciel.dto.teacher;

import com.example.studentnauczyciel.lib.ValidEmail;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDto {
    @NotNull(message = "First name can not be null")
    @Size(min = 3, max = 30, message = "First name length must be between 3 and 30")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @Size(min = 3, max = 30, message = "Last name length must be between 3 and 30")
    private String lastName;

    @Min(value = 18, message = "Age must be greater than 17")
    @Max(value = 200, message = "Age must be less than 200")
    private int age;

    @ValidEmail
    private String email;

    @NotNull(message = "Subject can not be null")
    @Size(min = 3, max = 30, message = "Subject length must be between 3 and 30")
    private String subject;
}
