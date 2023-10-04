package soft.media.lab.test.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import soft.media.lab.test.dto.PerformanceDTO;
import soft.media.lab.test.entity.Performance;
import soft.media.lab.test.repository.PerformanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {


    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public Performance getPerformanceByText(String text) {
        return performanceRepository.getPerformanceByText(text);
    }

    public List<PerformanceDTO> getAllPerformances() {
        return performanceRepository.findAll().stream().map(PerformanceDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public PerformanceDTO updatePerformance(Long id, PerformanceDTO performanceDTO) {
        return null;
    }
}
