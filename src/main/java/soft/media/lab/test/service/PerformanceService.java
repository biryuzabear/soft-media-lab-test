package soft.media.lab.test.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft.media.lab.test.dto.PerformanceDTO;
import soft.media.lab.test.entity.Performance;
import soft.media.lab.test.log.LoggingService;
import soft.media.lab.test.repository.PerformanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {


    private final PerformanceRepository performanceRepository;
    private final LoggingService loggingService;

    public PerformanceService(PerformanceRepository performanceRepository, LoggingService loggingService) {
        this.performanceRepository = performanceRepository;
        this.loggingService = loggingService;
    }

    public Performance getPerformanceByText(String text) {
        return performanceRepository.getPerformanceByText(text).orElseThrow(() -> new EntityNotFoundException("Performance with text '" + text + "' not found"));
    }

    public List<PerformanceDTO> getAllPerformances() {
        return performanceRepository.findAll().stream().map(e -> {
            loggingService.log("Получили: " + e);
            return new PerformanceDTO(e.getText());
        }).collect(Collectors.toList());
    }

    @Transactional
    public PerformanceDTO updatePerformance(Long id, PerformanceDTO performanceDTO) {
        Performance existingPerformance = performanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Performance with ID " + id + " not found"));
        existingPerformance.setText(performanceDTO.text());
        Performance updatedPerformance = performanceRepository.save(existingPerformance);
        return new PerformanceDTO(updatedPerformance.getText());
    }
}
