package soft.media.lab.test.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import soft.media.lab.test.dto.PerformanceDTO;
import soft.media.lab.test.service.PerformanceService;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {
    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping
    public List<PerformanceDTO> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @PutMapping("/{id}")
    public PerformanceDTO updatePerformance(@PathVariable Long id, @Valid @RequestBody PerformanceDTO performanceDTO) {
        return performanceService.updatePerformance(id, performanceDTO);
    }
}