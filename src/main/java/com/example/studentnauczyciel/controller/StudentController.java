package com.example.studentnauczyciel.controller;

import com.example.studentnauczyciel.dto.mapper.StudentMapper;
import com.example.studentnauczyciel.dto.mapper.TeacherMapper;
import com.example.studentnauczyciel.dto.student.StudentRequestDto;
import com.example.studentnauczyciel.dto.student.StudentResponseDto;
import com.example.studentnauczyciel.dto.teacher.TeacherResponseDto;
import com.example.studentnauczyciel.lib.Parser;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import com.example.studentnauczyciel.service.StudentService;
import com.example.studentnauczyciel.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping
    @ApiOperation(value = "Create a new student")
    public StudentResponseDto add(@RequestBody @Valid StudentRequestDto dto) {
        return studentMapper.toDto(studentService
                .save(studentMapper.toModel(dto)));
    }

    @PostMapping("/{studentId}/teachers/{teacherId}")
    @ApiOperation(value = "Add a teacher to a student")
    public Set<TeacherResponseDto> addTeacher(@PathVariable(value = "studentId") Long studentId,
                                              @PathVariable(value = "teacherId") Long teacherId) {
        return studentService.addTeacher(studentId, teacherId).stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a student by id")
    public StudentResponseDto get(@PathVariable(value = "id") Long id) {
        return studentMapper.toDto(studentService.findById(id));
    }

    @GetMapping("/by-full-name")
    @ApiOperation(value = "Get a student by first name and last name")
    public StudentResponseDto findByFirstAndLastName(@RequestParam String firstName,
                                                     @RequestParam String lastName) {
        return studentMapper.toDto(
                studentService.findByFirstNameAndLastName(firstName, lastName));
    }

    @GetMapping
    @ApiOperation(value = "Get a sorted list of students by parameters")
    public Set<StudentResponseDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer count,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Parser.getSortOrders(sortBy));
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return studentService.findAll(pageRequest).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{studentId}/teachers")
    @ApiOperation(value = "Get a sorted list of all teachers by student ID")
    public Set<TeacherResponseDto> getAllTeachersByStudentId(
            @PathVariable(value = "studentId") Long studentId) {
        studentService.findById(studentId);
        Set<Teacher> teachers = teacherService.findAllByStudentId(studentId);
        return teachers.stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toSet());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a student by id")
    public StudentResponseDto update(@PathVariable(value = "id") Long id,
                                     @RequestBody @Valid StudentRequestDto dto) {
        Student student = studentMapper.toModel(dto);
        student.setId(id);
        return studentMapper.toDto(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a student by id")
    public void delete(@PathVariable(value = "id") Long id) {
        studentService.deleteById(id);
    }

    @DeleteMapping("/{studentId}/teachers/{teacherId}")
    @ApiOperation(value = "Remove the teacher (by id) from the student list")
    public void deleteTeacher(@PathVariable(value = "studentId") Long studentId,
                              @PathVariable(value = "teacherId") Long teacherId) {
        teacherService.removeStudent(teacherId, studentId);
    }
}
