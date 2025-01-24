package com.product1.demo.model;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "sub_category")
public class SubCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

    @ManyToOne
    private Category category;
    private Long categoryid;
}
