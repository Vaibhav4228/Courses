package com.vaibhav.server.config;

import com.vaibhav.server.entity.Product;
import com.vaibhav.server.mappers.ProductMapper;
import com.vaibhav.server.mappers.ProductRequestMapper;
import com.vaibhav.server.repo.ProductRepository;
import com.vaibhav.shared.dto.ProductDTO;
import com.vaibhav.shared.dto.ProductRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class BatchConfig {

    @Bean
    @StepScope
    public ItemReader<ProductRequestDTO> itemReader(ProductRepository repository, ProductMapper productMapper) { // Change return type
        List<Product> products = repository.findAll();
        List<ProductRequestDTO> requestDTOs = new ArrayList<>();
        for (Product product: products) {
            requestDTOs.add(productMapper.toDto(product));
        }
        log.info("ItemReader loaded {} products from the database", requestDTOs.size());
        return new ListItemReader<>(requestDTOs);
    }

    @Bean
    public ItemProcessor<ProductRequestDTO, ProductDTO> itemProcessor(ProductRequestMapper requestMapper) { // Change input type, inject mapper
        return requestDTO -> {
            ProductDTO dto = requestMapper.toDto(requestDTO);
            double originalPrice = dto.getProductPrice();
            dto.setDiscountedPrice(originalPrice * 0.9);
            log.info("Processed Product: {} originalPrice: {} discountedPrice: {}",
                    dto.getProductName(), originalPrice, dto.getDiscountedPrice());
            return dto;
        };
    }


    @Bean
    public ItemWriter<ProductDTO> itemWriter(ProductRepository repository, ProductMapper mapper) {
        return items -> {
            List<Product> products = new ArrayList<>();
            for (ProductDTO item: items) {
                products.add(mapper.toEntity(item));
            }
            repository.saveAll(products);
            log.info("Saved {} processed products", items.size());
        };
    }



    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                     ItemReader<ProductRequestDTO> reader,
                     ItemProcessor<ProductRequestDTO, ProductDTO> processor,
                     ItemWriter<ProductDTO> writer) {

        return new StepBuilder("step", jobRepository)
                .<ProductRequestDTO, ProductDTO>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .listener(new SkipListener<ProductRequestDTO, ProductDTO>() {
                    @Override
                    public void onSkipInRead(Throwable t) {
                        log.warn("Skipped reading due to: {}", t.getMessage());
                    }
                    @Override
                    public void onSkipInWrite(ProductDTO item, Throwable t) {
                        log.warn("Skipped writing item: {} due to: {}", item, t.getMessage());
                    }
                    @Override
                    public void onSkipInProcess(ProductRequestDTO item, Throwable t) {
                        log.warn("Skipped processing item: {} due to: {}", item, t.getMessage());
                    }
                })
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}