package com.vaibhav.shared.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String productName;
    private double productPrice;
    private String productCategory;
}
