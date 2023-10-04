package soft.media.lab.test.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.entity.Performance;
import soft.media.lab.test.entity.Student;
import soft.media.lab.test.log.LoggingService;
import soft.media.lab.test.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PerformanceService performanceService;
    private final LoggingService loggingService;


    public StudentService(StudentRepository studentRepository, PerformanceService performanceService, LoggingService loggingService) {
        this.studentRepository = studentRepository;
        this.performanceService = performanceService;
        this.loggingService = loggingService;
    }

    public List<StudentWithPerformanceDTO> getAllStudentsWithPerformances() {
        return studentRepository.findAllWithPerformanceDetails().stream().peek(e -> loggingService.log("Получили: " + e)).collect(Collectors.toList());
    }

    public StudentWithPerformanceDTO getStudent(Long id) {
        return studentRepository.findByIdWithPerformanceDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Performance with ID " + id + " not found"));
    }

    @Transactional
    public StudentWithPerformanceDTO createStudent(StudentWithPerformanceDTO studentDTO) {
        Student student = new Student();
        return getStudentWithPerformanceDTO(studentDTO, student);
    }

    @Transactional
    public StudentWithPerformanceDTO updateStudent(Long id, StudentWithPerformanceDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return getStudentWithPerformanceDTO(studentDTO, student);
    }

    private StudentWithPerformanceDTO getStudentWithPerformanceDTO(StudentWithPerformanceDTO studentDTO, Student student) {
        student.setFullName(studentDTO.fullName());
        student.setBirthDate(studentDTO.birthDate());
        String performanceText = studentDTO.performance();

        if (performanceText != null && !performanceText.isBlank()) {
            Performance performance = performanceService.getPerformanceByText(performanceText);
            student.setPerformance(performance);
        }

        Student savedStudent = studentRepository.save(student);
        return new StudentWithPerformanceDTO(savedStudent.getFullName(), savedStudent.getBirthDate(), savedStudent.getPerformance().getText());
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
