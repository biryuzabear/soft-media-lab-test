package soft.media.lab.test.dto;

import jakarta.validation.constraints.NotBlank;

public record PerformanceDTO(@NotBlank String text) {
}