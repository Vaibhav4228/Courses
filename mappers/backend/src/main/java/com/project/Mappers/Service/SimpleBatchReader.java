package com.project.Mappers.Service;

import com.project.Mappers.Entity.PayloadEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SimpleBatchReader {

    private final EntityManagerFactory entityManagerFactory;

    public SimpleBatchReader(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Bean(name = "successfulPaymentsReader")
    @StepScope
    public JpaPagingItemReader<PayloadEntity> successfulPaymentsReader() {
        return new JpaPagingItemReaderBuilder<PayloadEntity>()
                .name("successfulPaymentsReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT p FROM PayloadEntity p")
                .pageSize(10)
                .build();
    }


    @Bean(name = "noOpProcessor")
    public ItemProcessor<PayloadEntity, PayloadEntity> noOpProcessor() {
        return item -> item;
    }

    @Bean(name = "noOpWriter")
    public ItemWriter<PayloadEntity> noOpWriter() {
        return items -> {

            log.info("Read " + items.size() + " successful payments from DB.");
        };
    }
}