package soft.media.lab.test.repository;

import soft.media.lab.test.entity.Performance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends R2dbcRepository<Performance, Long> {

}