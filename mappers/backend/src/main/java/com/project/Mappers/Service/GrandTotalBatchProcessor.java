package com.project.Mappers.Service;

import com.project.Mappers.Entity.InvoicesEntity;
import com.project.Mappers.Repository.InvoicesRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;

@Configuration
public class GrandTotalBatchProcessor {

    private final EntityManagerFactory entityManagerFactory;
    private final InvoicesRepository invoicesRepository;

    public GrandTotalBatchProcessor(EntityManagerFactory entityManagerFactory, InvoicesRepository invoicesRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.invoicesRepository = invoicesRepository;
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<InvoicesEntity> grandTotalItemReader(@Value("#{jobParameters['payloadId']}") Long payloadId) {
        return new JpaPagingItemReaderBuilder<InvoicesEntity>()
                .name("grandTotalItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT i FROM InvoicesEntity i WHERE i.payload.id = :payloadId")
                .parameterValues(Map.of("payloadId", payloadId))
                .pageSize(10)
                .build();
    }

    @Bean
    public ItemProcessor<InvoicesEntity, InvoicesEntity> grandTotalItemProcessor() {
        return new ItemProcessor<InvoicesEntity, InvoicesEntity>() {
            private double totalAmount = 0;

            @Override
            public InvoicesEntity process(InvoicesEntity invoice) {
                try {
                    double amount = Double.parseDouble(invoice.getInvoiceAmount());
                    totalAmount += amount;
                } catch (NumberFormatException e) {
                    System.out.println(" Error parsing invoice amount: " + invoice.getInvoiceAmount());
                }

                invoice.setGrandTotal(totalAmount);
                return invoice;
            }
        };
    }

    @Bean
    public ItemWriter<InvoicesEntity> grandTotalItemWriter() {
        return items -> {
            invoicesRepository.saveAll(items);
            System.out.println("Grand Total Updated for All Invoices in DB.");
        };
    }
}
