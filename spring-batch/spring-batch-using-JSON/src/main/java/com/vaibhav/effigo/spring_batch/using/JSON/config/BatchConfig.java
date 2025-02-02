package com.vaibhav.effigo.spring_batch.using.JSON.config;

import com.vaibhav.effigo.spring_batch.using.JSON.batch.ProductItemProcessor;
import com.vaibhav.effigo.spring_batch.using.JSON.batch.ProductItemReader;
import com.vaibhav.effigo.spring_batch.using.JSON.batch.ProductItemWriter;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private ProductItemReader productItemReader;

    @Autowired
    private ProductItemProcessor productItemProcessor;

    @Autowired
    private ProductItemWriter productItemWriter;

    @Bean
    public Step jsonProcessingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("jsonProcessingStep", jobRepository)
                .<DiscountedProduct, DiscountedProduct>chunk(5, transactionManager)
                .reader(productItemReader.jsonItemReader())
                .processor(productItemProcessor)
                .writer(productItemWriter)
                .build();
    }

    @Bean
    public Job jsonProcessingJob(JobRepository jobRepository, Step jsonProcessingStep) {
        return new JobBuilder("jsonProcessingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jsonProcessingStep)
                .build();
    }
}