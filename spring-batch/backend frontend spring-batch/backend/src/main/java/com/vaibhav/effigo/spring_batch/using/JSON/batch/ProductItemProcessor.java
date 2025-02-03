package com.vaibhav.effigo.spring_batch.using.JSON.batch;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import com.vaibhav.effigo.spring_batch.using.JSON.utils.DiscountCalculator;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductItemProcessor implements ItemProcessor<DiscountedProduct, DiscountedProduct> {
    @Override
    public DiscountedProduct process(DiscountedProduct discountedProduct) {
        if (discountedProduct == null) {
            return null;
        }
        double discountedPrice = DiscountCalculator.applyDiscount(discountedProduct.getOriginalPrice(), discountedProduct.getCategory());
        return new DiscountedProduct(
                discountedProduct.getId(),
                discountedProduct.getName(),
                discountedProduct.getOriginalPrice(),
                discountedPrice,
                discountedProduct.getCategory()
        );
    }
}