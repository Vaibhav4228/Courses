package com.vaibhav.server.controller;


import com.vaibhav.server.entity.Product;
import com.vaibhav.server.repo.ProductRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/l2")
public class L2Controller {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final ProductRepository repository;

    public L2Controller(JobLauncher jobLauncher, Job job, ProductRepository repository) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.repository = repository;
    }

    @PostMapping("/process")
    public ResponseEntity<List<Product>> process(@RequestBody List<Product> products) throws Exception {

        repository.saveAll(products);

        JobParameters parameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(job, parameters);


        List<Product> processedProducts = repository.findAll();
        return ResponseEntity.ok(processedProducts);
    }
}