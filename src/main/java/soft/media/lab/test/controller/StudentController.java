package soft.media.lab.test.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Flux<StudentWithPerformanceDTO> getAllStudents() {
        return studentService.getAllStudentsWithPerformances();
    }

    @GetMapping("/{id}")
    public Mono<StudentWithPerformanceDTO> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Mono<StudentWithPerformanceDTO> createStudent(@Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public Mono<StudentWithPerformanceDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}