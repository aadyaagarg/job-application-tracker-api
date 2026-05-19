package com.aadya.jobtracker.dto;

import com.aadya.jobtracker.model.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobRequest {

    @NotBlank(message="Company name required")
    private String companyName;

    @NotBlank(message="Job role required")
    private String jobRole;

    private String location;

    private LocalDate applicationDate;

    private LocalDate interviewDate;

    private String notes;

    @NotNull(message="Status required")
    private JobStatus status;
}