package com.example.studentnauczyciel.dto.teacher;

import com.example.studentnauczyciel.lib.ValidEmail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class TeacherRequestDto {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Min(value = 18)
    private Integer age;

    @ValidEmail
    private String email;

    @NotNull(message = "Subject can not be null")
    private String subject;
    private Set<Long> studentsIds;
}
