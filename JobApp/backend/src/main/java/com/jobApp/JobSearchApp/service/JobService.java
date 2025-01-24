package com.jobApp.JobSearchApp.service;

import com.jobApp.JobSearchApp.entity.JobPost;
import com.jobApp.JobSearchApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepo repo;

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public void addJob(JobPost jobPost){

        repo.save(jobPost);


    }

    public JobPost getJob(int postId){
        return repo.findById(postId).orElse((new JobPost()));

    }
    public List<JobPost> search(String keyword){

        return repo.searchPost(keyword);
    }
    public void updateJob(JobPost jobPost){

        repo.save(jobPost);
    }

    public void deleteJob(int postId){

        repo.deleteById(postId);
    }
}
