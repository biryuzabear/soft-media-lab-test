package soft.media.lab.test.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.repository.StudentRepository;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<StudentWithPerformanceDTO> getAllStudentsWithPerformances() {
        return studentRepository.getAllStudentsWithPerformances();
    }

    public Mono<StudentWithPerformanceDTO> getStudent(Long id) {
        return null;
    }

    public Mono<StudentWithPerformanceDTO> createStudent(StudentWithPerformanceDTO studentDTO) {
        return null;
    }

    public Mono<StudentWithPerformanceDTO> updateStudent(Long id, StudentWithPerformanceDTO studentDTO) {
        return null;
    }

    public void deleteStudent(Long id) {

    }
}
