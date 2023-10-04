package soft.media.lab.test.dto;

import jakarta.validation.constraints.NotBlank;
import soft.media.lab.test.entity.Performance;

public record PerformanceDTO(
        @NotBlank String text) {

    public static PerformanceDTO fromEntity(Performance performance) {
        return new PerformanceDTO(performance.getText());
    }
}