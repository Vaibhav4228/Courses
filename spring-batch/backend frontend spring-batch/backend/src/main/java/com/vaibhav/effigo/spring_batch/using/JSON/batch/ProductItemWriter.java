package com.vaibhav.effigo.spring_batch.using.JSON.batch;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import com.vaibhav.effigo.spring_batch.using.JSON.repository.DiscountedProductRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductItemWriter implements ItemWriter<DiscountedProduct> {

    @Autowired
    private DiscountedProductRepository discountedProductRepository;

    @Override
    public void write(Chunk<? extends DiscountedProduct> items) throws Exception {
        discountedProductRepository.saveAll(items);
        
    }

}