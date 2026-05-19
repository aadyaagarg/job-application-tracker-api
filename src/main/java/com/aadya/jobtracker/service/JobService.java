package com.aadya.jobtracker.service;

import com.aadya.jobtracker.dto.JobRequest;
import com.aadya.jobtracker.model.*;
import com.aadya.jobtracker.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class JobService {

    private final JobRepository jobRepository;

    public Job addJob(JobRequest request){

        Job job=new Job();

        job.setCompanyName(
                request.getCompanyName());

        job.setJobRole(
                request.getJobRole());

        job.setLocation(
                request.getLocation());

        job.setApplicationDate(
                request.getApplicationDate());

        job.setInterviewDate(
                request.getInterviewDate());

        job.setNotes(
                request.getNotes());

        job.setStatus(
                request.getStatus());

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){

        return jobRepository.findAll();
    }

    public Job getJobById(Long id){

        return jobRepository.findById(id)
                .orElse(null);
    }

    public void deleteJob(Long id){

        jobRepository.deleteById(id);
    }

    public List<Job> filterByStatus(
            JobStatus status){

        return jobRepository
                .findByStatus(status);
    }

    public List<Job> searchByCompany(
            String company){

        return jobRepository
                .findByCompanyNameContaining(company);
    }

    public Job updateStatus(
            Long id,
            JobStatus status){

        Job job=jobRepository.findById(id)
                .orElse(null);

        if(job==null){

            return null;
        }

        job.setStatus(status);

        return jobRepository.save(job);
    }
}