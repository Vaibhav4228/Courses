package com.jobApp.JobSearchApp.service;

import com.jobApp.JobSearchApp.entity.JobPost;
import com.jobApp.JobSearchApp.repo.JobRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepo repo;

    public JobService(JobRepo repo) {
        this.repo = repo;
    }

    public List<JobPost> getAllJobs() {
        return repo.getAllJobs();
    }
}
