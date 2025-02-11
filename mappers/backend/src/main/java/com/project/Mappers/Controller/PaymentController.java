//package com.project.Mappers.Controller;
//
//import com.project.Mappers.DTO.PaymentExternalDTO;
//import com.project.Mappers.DTO.PaymentInternalDTO;
//import com.project.Mappers.Entity.PayloadEntity;
//import com.project.Mappers.Service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/payment")
//public class PaymentController {
//
//    private PaymentService paymentService;
//
//    @Autowired
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/change")
//    public ResponseEntity<PaymentInternalDTO> convertToInternal(@RequestBody PaymentExternalDTO paymentExternalDTO) {
//        PaymentInternalDTO paymentInternalDTO = paymentService.convertToInternal(paymentExternalDTO);
//        return ResponseEntity.ok(paymentInternalDTO);
//    }
//
//    @PostMapping("/Saving-DB")
//    public ResponseEntity<?> savePayload(@RequestBody PaymentInternalDTO paymentInternalDTO) {
//        try {
//            PayloadEntity savedEntity = paymentService.savePayload(paymentInternalDTO);
//            return ResponseEntity.ok(savedEntity);
//        } catch (RuntimeException ex) {
//
//            Map<String, String> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Validation failed");
//            errorResponse.put("message", ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }
//    }
//
//}

package com.project.Mappers.Controller;

import com.project.Mappers.DTO.PaymentExternalDTO;
import com.project.Mappers.DTO.PaymentInternalDTO;
import com.project.Mappers.Entity.FailedPaymentsEntity;
import com.project.Mappers.Entity.PayloadEntity;
import com.project.Mappers.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private  PaymentService paymentService;



    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/change")
    public ResponseEntity<PaymentInternalDTO> convertToInternal(@RequestBody PaymentExternalDTO paymentExternalDTO) {
        PaymentInternalDTO paymentInternalDTO = paymentService.convertToInternal(paymentExternalDTO);
        return ResponseEntity.ok(paymentInternalDTO);
    }

    @PostMapping("/Saving-DB")
    public ResponseEntity<?> savePayload(@RequestBody PaymentInternalDTO paymentInternalDTO) {
        try {

            PayloadEntity savedEntity = paymentService.savePayload(paymentInternalDTO);
            return ResponseEntity.ok(savedEntity);
        } catch (RuntimeException ex) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Validation failed");
            errorResponse.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentById(@PathVariable Long id){
        try {
            paymentService.deletePaymentById(id);
            return ResponseEntity.ok("payment with id " + id + "deleted sucessfully");


        }
        catch (RuntimeException err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());


        }
    }

    @GetMapping("/Successfull-Payment")
    public ResponseEntity<List<PayloadEntity>> getAllSuccessfulPayments(){
        return ResponseEntity.ok(paymentService.getAllSuccessfulPayments());

    }

    @GetMapping("/Failed-Payments")
    public ResponseEntity<List<FailedPaymentsEntity>> getAllFailedPayments(){
        return ResponseEntity.ok(paymentService.getAllFailedPayments());
    }
}

