package com.aadya.jobtracker.controller;

import com.aadya.jobtracker.dto.JobRequest;
import com.aadya.jobtracker.model.*;
import com.aadya.jobtracker.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor

public class JobController {

    private final JobService jobService;

    @PostMapping
    public Job addJob(
            @Valid @RequestBody JobRequest request){

        return jobService.addJob(request);
    }

    @GetMapping
    public List<Job> getAllJobs(){

        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(
            @PathVariable Long id){

        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id){

        jobService.deleteJob(id);

        return "Deleted Successfully";
    }

    @GetMapping("/status")
    public List<Job> filterByStatus(
            @RequestParam JobStatus status){

        return jobService.filterByStatus(status);
    }

    @GetMapping("/search")
    public List<Job> searchByCompany(
            @RequestParam String company){

        return jobService.searchByCompany(company);
    }

    @PatchMapping("/{id}/status")
    public Job updateStatus(
            @PathVariable Long id,
            @RequestParam JobStatus status){

        return jobService.updateStatus(
                id,
                status
        );
    }
}