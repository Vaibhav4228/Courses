package com.example.projectioninspringdatajpa.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private Double price;
    private int quantity;
    private String description;
    private String date;
    private Integer rating;
}
