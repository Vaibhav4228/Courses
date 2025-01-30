package com.vaibhav.effigo.spring_batch.using.JSON.batch;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import com.vaibhav.effigo.spring_batch.using.JSON.utils.DiscountCalculator;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductItemProcessor implements ItemProcessor<Product, DiscountedProduct> {
    @Override
    public DiscountedProduct process(Product product) {
        if (product == null) {
            return null;
        }
        double discountedPrice = DiscountCalculator.applyDiscount(product.getPrice(), product.getCategory());
        return new DiscountedProduct(
                product.getId(),
                product.getName(),
                product.getPrice(),
                discountedPrice,
                product.getCategory()
        );
    }
}
