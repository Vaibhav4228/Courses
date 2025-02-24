package com.vaibhav.shared.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private double productPrice;
    private String productCategory;
    private double discountedPrice;
}
