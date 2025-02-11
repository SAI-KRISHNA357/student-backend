package com.assignment.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

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
    private String age;

    @Column(name = "mobile", nullable = false)
    @NotEmpty(message = "Mobile is required") // Validates non-empty
    private String mobile;
}
