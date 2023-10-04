package soft.media.lab.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import soft.media.lab.test.dto.PerformanceDTO;
import soft.media.lab.test.service.PerformanceService;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
@Tag(name = "Performance", description = "API for managing performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @Operation(
            summary = "Get a list of all performances",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @GetMapping
    public List<PerformanceDTO> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @Operation(
            summary = "Update an existing performance",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated performance"),
                    @ApiResponse(responseCode = "404", description = "Performance not found"),
                    @ApiResponse(responseCode = "403", description = "Value not valid"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @PutMapping("/{id}")
    public PerformanceDTO updatePerformance(
            @Parameter(description = "ID of the performance to be updated") @PathVariable Long id,
            @Parameter(
                    description = "Updated performance data",
                    examples = {
                            @ExampleObject(value = "{\"text\":\"Sample performance\"}")
                    }
            )
            @Valid @RequestBody PerformanceDTO performanceDTO) {
        return performanceService.updatePerformance(id, performanceDTO);
    }
}