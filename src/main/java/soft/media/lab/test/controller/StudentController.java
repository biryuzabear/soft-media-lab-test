package soft.media.lab.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "API for managing students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get a list of all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public List<StudentWithPerformanceDTO> getAllStudents() {
        return studentService.getAllStudentsWithPerformances();
    }

    @Operation(summary = "Get a specific student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/{id}")
    public StudentWithPerformanceDTO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created student"),
            @ApiResponse(responseCode = "403", description = "Value not valid"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping
    public StudentWithPerformanceDTO createStudent(@Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @Operation(summary = "Update a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated student"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Value not valid"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping("/{id}")
    public StudentWithPerformanceDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentWithPerformanceDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @Operation(summary = "Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted student"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}