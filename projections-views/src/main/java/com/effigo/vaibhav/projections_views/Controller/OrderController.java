package com.effigo.vaibhav.projections_views.Controller;

import com.effigo.vaibhav.projections_views.DTOs.OrderDTO;

import com.effigo.vaibhav.projections_views.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> getAllOrders(Pageable pageable) {
        Page<OrderDTO> orders = orderService.getPaginatedOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        return ResponseEntity.ok("Order created successfully!");
    }
}
