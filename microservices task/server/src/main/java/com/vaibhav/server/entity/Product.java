package com.vaibhav.server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String productName;
    private double productPrice;
    private String productCategory;
    private double discountedPrice;

    public Product() {}
    
    public Product(String productName, double productPrice, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }
    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public double getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    public double getDiscountedPrice() {
        return discountedPrice;
    }
    
    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", productName='" + productName + '\'' +
               ", productPrice=" + productPrice +
               ", productCategory='" + productCategory + '\'' +
               ", discountedPrice=" + discountedPrice +
               '}';
    }
}
