package com.vaibhav.cleint_1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Map<String, Object>>> sendDataToL2(@RequestBody List<Map<String, Object>> requestData) {
        ResponseEntity<List> response = restTemplate.postForEntity(
                l2ServerUrl + "/api/l2/process",
                requestData,
                List.class
        );

        return ResponseEntity.ok(response.getBody());
    }
}