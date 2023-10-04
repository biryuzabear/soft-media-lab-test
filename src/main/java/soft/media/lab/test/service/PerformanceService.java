package soft.media.lab.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import soft.media.lab.test.dto.PerformanceDTO;
import soft.media.lab.test.repository.PerformanceRepository;

@Service
public class PerformanceService {


    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public Flux<PerformanceDTO> getAllPerformances() {
        return performanceRepository.findAll().map(PerformanceDTO::fromEntity);
    }

    public Mono<PerformanceDTO> updatePerformance(Long id, PerformanceDTO performanceDTO) {
        return null;
    }
}
