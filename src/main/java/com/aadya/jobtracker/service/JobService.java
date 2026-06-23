package com.aadya.jobtracker.service;

import com.aadya.jobtracker.dto.JobRequest;
import com.aadya.jobtracker.model.Job;
import com.aadya.jobtracker.model.JobStatus;
import com.aadya.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class JobService {

    private final JobRepository jobRepository;

    public String addJob(JobRequest request) {
        if (jobRepository.existsByCompanyNameAndJobRole(request.getCompanyName(), request.getJobRole())) {
            return "Application already exists";
        }
        Job job = new Job();
        job.setCompanyName(request.getCompanyName());

        job.setJobRole(request.getJobRole());

        job.setLocation(request.getLocation());

        job.setApplicationDate(request.getApplicationDate());

        job.setInterviewDate(request.getInterviewDate());

        job.setNotes(request.getNotes());

        job.setStatus(request.getStatus());
        jobRepository.save(job);
        return "Job added successfully";
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> filterByStatus(JobStatus status) {
        return jobRepository.findByStatus(status);
    }

    public List<Job> searchByCompany(String company) {
        return jobRepository.findByCompanyNameContaining(company);
    }

    public Job updateStatus(Long id, JobStatus status) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return null;
        }
        job.setStatus(status);
        return jobRepository.save(job);
    }

    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalApplications", jobRepository.count());
        stats.put("applied", jobRepository.countByStatus(JobStatus.APPLIED));
        stats.put("oa", jobRepository.countByStatus(JobStatus.OA));
        stats.put("interview", jobRepository.countByStatus(JobStatus.INTERVIEW));
        stats.put("offer", jobRepository.countByStatus(JobStatus.OFFER));
        stats.put("rejected", jobRepository.countByStatus(JobStatus.REJECTED));
        return stats;
    }
}