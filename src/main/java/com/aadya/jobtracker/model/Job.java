package com.aadya.jobtracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Job {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String jobRole;

    private String location;

    private LocalDate applicationDate;

    private LocalDate interviewDate;

    private String notes;

    @Enumerated(EnumType.STRING)
    private JobStatus status;
}