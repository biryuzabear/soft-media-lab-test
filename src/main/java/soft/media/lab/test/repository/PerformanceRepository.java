package soft.media.lab.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.media.lab.test.entity.Performance;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Optional<Performance> getPerformanceByText(String text);

}