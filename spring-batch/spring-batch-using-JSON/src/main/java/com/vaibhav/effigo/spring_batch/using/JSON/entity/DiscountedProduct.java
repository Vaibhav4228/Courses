package com.vaibhav.effigo.spring_batch.using.JSON.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "discounted_productss")
public class DiscountedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private String name;

    private Double originalPrice;

    private Double discountedPrice;

    private String category;

    public DiscountedProduct() {
    }

    public DiscountedProduct( String name, Double originalPrice, Double discountedPrice, String category) {
     
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.category = category;
    }

    public DiscountedProduct(Long id, String name, Double originalPrice, double discountedPrice, String category) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getOriginalProductId() {
//        return originalProductId;
//    }
//
//    public void setOriginalProductId(Long originalProductId) {
//        this.originalProductId = originalProductId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}