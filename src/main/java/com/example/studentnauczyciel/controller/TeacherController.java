package com.example.studentnauczyciel.controller;

import com.example.studentnauczyciel.dto.mapper.StudentMapper;
import com.example.studentnauczyciel.dto.mapper.TeacherMapper;
import com.example.studentnauczyciel.dto.teacher.TeacherRequestDto;
import com.example.studentnauczyciel.dto.student.StudentResponseDto;
import com.example.studentnauczyciel.dto.teacher.TeacherResponseDto;
import com.example.studentnauczyciel.lib.Parser;
import com.example.studentnauczyciel.model.Student;
import com.example.studentnauczyciel.model.Teacher;
import com.example.studentnauczyciel.service.StudentService;
import com.example.studentnauczyciel.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
@RequestMapping("/api/teachers")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    @ApiOperation(value = "Create a new teacher")
    public TeacherResponseDto add(@RequestBody @Valid TeacherRequestDto dto) {
        return teacherMapper.toDto(teacherService
                .save(teacherMapper.toModel(dto)));
    }

    @PostMapping("/{teacherId}/students/{studentId}")
    @ApiOperation(value = "Add a student to a teacher")
    public Set<StudentResponseDto> addStudent(@PathVariable(value = "teacherId") Long teacherId,
                                              @PathVariable(value = "studentId") Long studentId) {
        return teacherService.addStudent(teacherId, studentId).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/by-full-name")
    @ApiOperation(value = "Get a teacher by first name and last name")
    public TeacherResponseDto findByFirstAndLastName(@RequestParam String firstName,
                                                     @RequestParam String lastName) {
        return teacherMapper.toDto(teacherService.findByFirstNameAndLastName(firstName, lastName));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a teacher by id")
    public TeacherResponseDto get(@PathVariable(value = "id") Long id) {
        return teacherMapper.toDto(teacherService.findById(id));
    }

    @GetMapping("/{teacherId}/students")
    @ApiOperation(value = "Get a sorted list of all students by teacher ID")
    public List<StudentResponseDto> getAllStudentsByTeacherId(
            @PathVariable(value = "teacherId") Long teacherId) {
        teacherService.findById(teacherId);
        List<Student> students = studentService.findAllByTeacherId(teacherId);
        return students.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    @ApiOperation(value = "Get a sorted list of teachers by parameters")
    public List<TeacherResponseDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer count,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Parser.getSortOrders(sortBy));
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return teacherService.findAll(pageRequest).stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a teacher by id")
    public TeacherResponseDto update(@PathVariable(value = "id") Long id,
                                     @RequestBody @Valid TeacherRequestDto dto) {
        Teacher teacher = teacherMapper.toModel(dto);
        teacher.setId(id);
        return teacherMapper.toDto(teacherService.save(teacher));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a teacher by id")
    public void delete(@PathVariable(value = "id") Long id) {
        teacherService.delete(id);
    }

    @DeleteMapping("/{teacherId}/students/{studentId}")
    @ApiOperation(value = "Remove the student (by id) from the teacher list")
    public void deleteTeacher(@PathVariable(value = "teacherId") Long teacherId,
                              @PathVariable(value = "studentId") Long studentId) {
        teacherService.removeStudent(teacherId, studentId);
    }
}
