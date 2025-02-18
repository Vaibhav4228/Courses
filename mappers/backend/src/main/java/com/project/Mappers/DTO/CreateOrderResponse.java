package com.project.Mappers.DTO;

public class CreateOrderResponse {
    private String orderId;

    public CreateOrderResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
