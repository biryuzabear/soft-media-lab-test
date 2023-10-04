package soft.media.lab.test.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PerformanceService performanceService;

    public StudentService(StudentRepository studentRepository, PerformanceService performanceService) {
        this.studentRepository = studentRepository;
        this.performanceService = performanceService;
    }

    public List<StudentWithPerformanceDTO> getAllStudentsWithPerformances() {
        return studentRepository.findAllWithPerformanceDetails();
    }

    public StudentWithPerformanceDTO getStudent(Long id) {
        return studentRepository.findByIdWithPerformanceDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Performance with ID " + id + " not found"));
    }

    @Transactional
    public StudentWithPerformanceDTO createStudent(StudentWithPerformanceDTO studentDTO) {
        return null;
    }

    public StudentWithPerformanceDTO updateStudent(Long id, StudentWithPerformanceDTO studentDTO) {
        return null;
    }

    public void deleteStudent(Long id) {

    }
}
