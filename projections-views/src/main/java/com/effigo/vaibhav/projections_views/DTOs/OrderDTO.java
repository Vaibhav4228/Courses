package com.effigo.vaibhav.projections_views.DTOs;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String productName;
    private Double price;
    private LocalDateTime orderDate;
    private Long userId;
}
