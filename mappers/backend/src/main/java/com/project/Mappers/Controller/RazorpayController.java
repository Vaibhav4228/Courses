package com.project.Mappers.Controller;

import com.project.Mappers.DTO.CreateOrderRequest;
import com.project.Mappers.DTO.CreateOrderResponse;
import com.project.Mappers.Service.RazorpayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/razorpay")
@CrossOrigin(origins = "*")
public class RazorpayController {

    private final RazorpayService razorpayService;

    public RazorpayController(RazorpayService razorpayService) {
        this.razorpayService = razorpayService;
    }


    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            String orderId = razorpayService.createOrder(
                request.getAmount(),
                request.getCurrency(),
                request.getReceipt()
            );
            return ResponseEntity.ok(new CreateOrderResponse(orderId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
