package com.project.Mappers.Controller;

import com.project.Mappers.DTO.PaymentExternalDTO;
import com.project.Mappers.DTO.PaymentInternalDTO;
import com.project.Mappers.Entity.PayloadEntity;
import com.project.Mappers.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/change")
    public ResponseEntity<PaymentInternalDTO> convertToInternal(@RequestBody PaymentExternalDTO paymentExternalDTO) {
        PaymentInternalDTO paymentInternalDTO = paymentService.convertToInternal(paymentExternalDTO);
        return ResponseEntity.ok(paymentInternalDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<PayloadEntity> savePayload(@RequestBody PaymentInternalDTO paymentInternalDTO) {
        PayloadEntity savedEntity = paymentService.savePayload(paymentInternalDTO);
        return ResponseEntity.ok(savedEntity);
    }

}
