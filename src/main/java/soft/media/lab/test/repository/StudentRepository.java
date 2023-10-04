package soft.media.lab.test.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.entity.Student;

@Repository
public interface StudentRepository extends R2dbcRepository<Student, Long> {

    @Query("""
            SELECT s.full_name as full_name, s.birth_date as birth_date, p.text as performance
            FROM student s\s
            INNER JOIN performance p ON s.performance_id = p.id""")
    Flux<StudentWithPerformanceDTO> getAllStudentsWithPerformances();

}