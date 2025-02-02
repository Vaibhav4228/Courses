package com.vaibhav.effigo.spring_batch.using.JSON.batch;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@Component
public class ProductItemReader {
    public ItemReader<DiscountedProduct> jsonItemReader() {
        JacksonJsonObjectReader<DiscountedProduct> jsonObjectReader = new JacksonJsonObjectReader<>(DiscountedProduct.class);

        return new JsonItemReaderBuilder<DiscountedProduct>()
                .jsonObjectReader(jsonObjectReader)
                .resource(new ClassPathResource("products.json"))
                .name("productJsonItemReader")
                .build();
    }


}