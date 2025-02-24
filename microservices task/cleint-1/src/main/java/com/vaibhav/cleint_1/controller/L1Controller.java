package com.vaibhav.cleint_1.controller;

import com.vaibhav.shared.dto.ProductDTO;
import com.vaibhav.shared.dto.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/l1")
public class L1Controller {

    private final RestTemplate restTemplate;

    @Value("${l2.server.url}")
    private String l2ServerUrl;

    public L1Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<List<ProductDTO>> sendDataToL2(@RequestBody List<ProductRequestDTO> requestData) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (ProductRequestDTO requestDTO: requestData) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(requestDTO.getProductName());
            productDTO.setProductPrice(requestDTO.getProductPrice());
            productDTO.setProductCategory(requestDTO.getProductCategory());
            productDTOs.add(productDTO);
        }

        ResponseEntity<List<ProductDTO>> response = restTemplate.exchange(
                l2ServerUrl + "/api/l2/process",
                HttpMethod.POST,
                new HttpEntity<>(productDTOs),
                new ParameterizedTypeReference<List<ProductDTO>>() {}
        );


        return ResponseEntity.ok(response.getBody());
    }
}