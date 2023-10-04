package soft.media.lab.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import soft.media.lab.test.entity.Student;

import java.sql.Date;

public record StudentDTO(
        @NotBlank String fullName,
        @Past Date birthDate) {

    public static Student toEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFullName(studentDTO.fullName());
        student.setBirthDate(studentDTO.birthDate());
        return student;
    }

    public static StudentDTO fromEntity(Student student) {
        return new StudentDTO(student.getFullName(), student.getBirthDate());
    }
}