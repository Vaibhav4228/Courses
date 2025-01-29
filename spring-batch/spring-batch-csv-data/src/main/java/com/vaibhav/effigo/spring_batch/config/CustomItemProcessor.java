package com.vaibhav.effigo.spring_batch.config;

import com.vaibhav.effigo.spring_batch.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product item) throws Exception {
        try {
            System.out.println("Processing Product: " + item.getTitle());

            int discountPer = Integer.parseInt(item.getDiscount().trim());
            double originalPrice = Double.parseDouble(item.getPrice().trim());

            double discount = (discountPer / 100.0) * originalPrice;
            double finalPrice = originalPrice - discount;

            item.setDiscountedPrice(String.format("%.2f", finalPrice));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return item;
    }

}
