package soft.media.lab.test.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentWithPerformanceDTO> getAllStudents() {
        return studentService.getAllStudentsWithPerformances();
    }

    @GetMapping("/{id}")
    public StudentWithPerformanceDTO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public StudentWithPerformanceDTO createStudent(@Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentWithPerformanceDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}