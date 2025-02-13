package com.project.Mappers.config;

import com.project.Mappers.Entity.InvoicesEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
public class BatchConfig {

    @Bean
    public Job grandTotalJob(JobRepository jobRepository, @Qualifier("grandTotalStep") Step grandTotalStep) {
        return new JobBuilder("grandTotalJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(grandTotalStep)
                .build();
    }

    @Bean
    public Step grandTotalStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                               @Qualifier("grandTotalItemReader") ItemReader<InvoicesEntity> reader,
                               @Qualifier("grandTotalItemProcessor") ItemProcessor<InvoicesEntity, InvoicesEntity> processor,
                               @Qualifier("grandTotalItemWriter") ItemWriter<InvoicesEntity> writer) {
        return new StepBuilder("grandTotalStep", jobRepository)
                .<InvoicesEntity, InvoicesEntity>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
