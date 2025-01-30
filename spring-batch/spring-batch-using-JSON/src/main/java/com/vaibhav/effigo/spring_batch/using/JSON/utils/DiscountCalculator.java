package com.vaibhav.effigo.spring_batch.using.JSON.utils;

public class DiscountCalculator {
    public static double applyDiscount(double price, String category) {
        if (price < 0) {
            throw new IllegalArgumentException("price is not -ve");
        }

        double discount;
        switch (category.toLowerCase()) {
            case "electronics":
                discount = 0.10;
                break;
            case "clothing":
                discount = 0.15;
                break;
            case "groceries":
                discount = 0.05;
                break;
            default:
                discount = 0.03;
        }

        return price * (1 - discount);
    }
}

