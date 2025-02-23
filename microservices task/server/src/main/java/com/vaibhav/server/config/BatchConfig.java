package com.vaibhav.server.config;

import com.vaibhav.server.entity.Product;
import com.vaibhav.server.repo.ProductRepository;
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

import java.util.List;

@Slf4j
@Configuration
public class BatchConfig {

    @Bean
    @StepScope
    public ItemReader<Product> itemReader(ProductRepository repository) {
        List<Product> products = repository.findAll();
        log.info("ItemReader loaded {} products from the database", products.size());
        return new ListItemReader<>(products);
    }


    @Bean
    public ItemProcessor<Product, Product> itemProcessor() {
        return product -> {
            double originalPrice = product.getProductPrice();
            product.setDiscountedPrice(originalPrice * 0.9);
            log.info("Processed Product: {} originalPrice: {} discountedPrice: {}",
                    product.getProductName(), originalPrice, product.getDiscountedPrice());
            return product;
        };
    }


    @Bean
    public ItemWriter<Product> itemWriter(ProductRepository repository) {
        return items -> {
            repository.saveAll(items);
            log.info("Saved {} processed products", items.size());
        };
    }


    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                     ItemReader<Product> reader,
                     ItemProcessor<Product, Product> processor,
                     ItemWriter<Product> writer) {

        return new StepBuilder("step", jobRepository)
                .<Product, Product>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .listener(new SkipListener<Product, Product>() {
                    @Override
                    public void onSkipInRead(Throwable t) {
                        log.warn("Skipped reading due to: {}", t.getMessage());
                    }
                    @Override
                    public void onSkipInWrite(Product item, Throwable t) {
                        log.warn("Skipped writing item: {} due to: {}", item, t.getMessage());
                    }
                    @Override
                    public void onSkipInProcess(Product item, Throwable t) {
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
