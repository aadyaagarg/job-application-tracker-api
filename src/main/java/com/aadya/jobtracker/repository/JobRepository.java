package com.aadya.jobtracker.repository;

import com.aadya.jobtracker.model.Job;
import com.aadya.jobtracker.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository
        extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);

    List<Job> findByCompanyNameContaining(String companyName);

    long countByStatus(JobStatus status);

    boolean existsByCompanyNameAndJobRole(String companyName, String jobRole);

}