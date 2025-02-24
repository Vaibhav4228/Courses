package com.vaibhav.server.controller;

import com.vaibhav.server.entity.Product;
import com.vaibhav.server.mappers.ProductMapper;
import com.vaibhav.server.mappers.ProductRequestMapper;
import com.vaibhav.server.repo.ProductRepository;
import com.vaibhav.shared.dto.ProductDTO;
import com.vaibhav.shared.dto.ProductRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/l2")
public class L2Controller {

    @Autowired
   JobLauncher jobLauncher;

    @Autowired
    private final Job job;

    @Autowired
    ProductRepository repository;

    @Autowired
   ProductMapper mapper;

    @Autowired
    ProductRequestMapper requestMapper;

//    public L2Controller(JobLauncher jobLauncher, Job job, ProductRepository repository, ProductMapper mapper, ProductRequestMapper requestMapper) {
//        this.jobLauncher = jobLauncher;
//        this.job = job;
//        this.repository = repository;
//        this.mapper = mapper;
//        this.requestMapper = requestMapper;
//    }

    @PostMapping("/process")
    public ResponseEntity<List<ProductDTO>> process(@RequestBody List<ProductRequestDTO> requestDTOs) throws Exception {

        List<ProductDTO> productDTOs = new ArrayList<>();
        for (ProductRequestDTO requestDTO: requestDTOs) {
            productDTOs.add(requestMapper.toDto(requestDTO));
        }


        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO: productDTOs) {
            products.add(mapper.toEntity(productDTO));
        }

        repository.saveAll(products);

        JobParameters parameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(job, parameters);


        List<ProductDTO> processedDTOs = new ArrayList<>();
        for (Product product: repository.findAll()) {
            processedDTOs.add(mapper.toProductDto(product));
        }

        return ResponseEntity.ok(processedDTOs);
    }
}
