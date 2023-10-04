package soft.media.lab.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.sql.Date;

public record StudentWithPerformanceDTO(@NotBlank String fullName, @Past Date birthDate, String performance) {

}