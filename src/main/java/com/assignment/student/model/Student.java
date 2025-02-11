package com.assignment.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "student")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "name is required") // Validates non-empty
    private String name;

    @Column(name = "class_name", nullable = false)
    @NotEmpty(message = "class name is required") // Validates non-empty
    private String className;

    @Column(name = "age", nullable = false)
    @NotEmpty(message = "age is required") // Validates non-empty
    @Positive
    @Min(value = 0, message = "Min Age should be 0")
    @Max(value = 100, message = "Max Age should be 100")
    private String age;

    @Column(name = "mobile", nullable = false)
    @NotEmpty(message = "Mobile is required") // Validates non-empty
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number.")
    private String mobile;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
}
