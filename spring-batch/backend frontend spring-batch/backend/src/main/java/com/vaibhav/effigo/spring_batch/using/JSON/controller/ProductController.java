package com.vaibhav.effigo.spring_batch.using.JSON.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import com.vaibhav.effigo.spring_batch.using.JSON.repository.DiscountedProductRepository;
import com.vaibhav.effigo.spring_batch.using.JSON.repository.ProductRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:5173/")
public class ProductController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jsonProcessingJob;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountedProductRepository discountedProductRepository;

    private static final String FILE_PATH = "src/main/resources/products.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/process")
    public ResponseEntity<String> startBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(jsonProcessingJob, jobParameters);
            return ResponseEntity.ok("Batch job started successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(" error aeises: " + e.getMessage());
        }
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return ResponseEntity.ok(products);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<DiscountedProduct> product = discountedProductRepository.findById(id);
        if (product.isPresent()) {
            discountedProductRepository.deleteById(id);
            return ResponseEntity.ok("product deleted");
        } else {
            return ResponseEntity.status(404).body("product not found.");
        }
    }

    @GetMapping("/discounted")
    public ResponseEntity<List<DiscountedProduct>> getAllDiscountedProducts() {
        List<DiscountedProduct> discountedProducts = discountedProductRepository.findAll();
        return ResponseEntity.ok(discountedProducts);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        List<Product> products = readProductsFromFile();
        products.add(product);
        writeProductsToFile(products);
        return ResponseEntity.ok("done");
    }

    private List<Product> readProductsFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeProductsToFile(List<Product> products) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}