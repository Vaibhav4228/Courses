package com.jobApp.JobSearchApp.controller;


import com.jobApp.JobSearchApp.entity.JobPost;
import com.jobApp.JobSearchApp.service.JobService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobPosts")
@CrossOrigin(origins = "http://localhost:5173/")
public class JobController {

    @Autowired
    JobService service;

    @GetMapping
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("/keyword/{keyword}")
    public List<JobPost> searchKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }
    @Transactional
    @GetMapping("/{postId}")
    public JobPost getJob(@PathVariable int postId) {
        return service.getJob(postId);
    }
    @Transactional
    @PostMapping
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @Transactional
    @PutMapping
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @Transactional
    @DeleteMapping("/{postId}")
    public String deleteJob(@PathVariable  int postId){
        service.deleteJob(postId);
        return "removed job 😎" + postId;
    }



}
