package com.project.Mappers.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private double amount;
    private String currency;
    private String receipt;

}

