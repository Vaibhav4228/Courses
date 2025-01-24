package com.jobApp.JobSearchApp.controller;


import com.jobApp.JobSearchApp.entity.JobPost;
import com.jobApp.JobSearchApp.service.JobService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/jobPosts")

public class JobController {

    @Autowired
    JobService service;

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobs(){

        return service.getAllJobs();
    }

    @GetMapping("/jobPosts/keyword/{keyword}")
    public List<JobPost> searchKyeword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }


    @GetMapping("/jobPosts/{postId}")
    public JobPost getJob(@PathVariable int postId) {

        return service.getJob(postId);
    }

    @PostMapping("/jobPosts")
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }


    @PutMapping("/jobPosts/{id}")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }


    @DeleteMapping("/jobPosts/{postId}")
    public String deleteJob(@PathVariable  int postId){
        service.deleteJob(postId);
        return "removed job 😎" + postId;
    }



}
