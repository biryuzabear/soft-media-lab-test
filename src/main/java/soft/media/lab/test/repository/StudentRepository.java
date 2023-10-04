package soft.media.lab.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft.media.lab.test.dto.StudentWithPerformanceDTO;
import soft.media.lab.test.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new soft.media.lab.test.dto.StudentWithPerformanceDTO(s.fullName, s.birthDate, p.text) FROM Student s LEFT JOIN s.performance p")
    List<StudentWithPerformanceDTO> findAllWithPerformanceDetails();

    @Query("SELECT new soft.media.lab.test.dto.StudentWithPerformanceDTO(s.fullName, s.birthDate, p.text) FROM Student s LEFT JOIN s.performance p where s.id = ?1")
    Optional<StudentWithPerformanceDTO> findByIdWithPerformanceDetails(Long id);

}