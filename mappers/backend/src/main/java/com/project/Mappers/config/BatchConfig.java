package com.project.Mappers.config;

import com.project.Mappers.Entity.PayloadEntity;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job simpleReadJob(JobRepository jobRepository, @Qualifier("simpleReadStep") Step simpleReadStep) {
        return new JobBuilder("simpleReadJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleReadStep)
                .build();
    }

    @Bean
    public Step simpleReadStep(JobRepository jobRepository,
                               PlatformTransactionManager transactionManager,
                               @Qualifier("successfulPaymentsReader") ItemReader<PayloadEntity> reader,
                               @Qualifier("noOpProcessor") ItemProcessor<PayloadEntity, PayloadEntity> processor,
                               @Qualifier("noOpWriter") ItemWriter<PayloadEntity> writer) {
        return new StepBuilder("simpleReadStep", jobRepository)
                .<PayloadEntity, PayloadEntity>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
