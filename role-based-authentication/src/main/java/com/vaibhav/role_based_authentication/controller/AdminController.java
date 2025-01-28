package com.vaibhav.role_based_authentication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> allAdminUser(){
        return ResponseEntity.ok("hii this is Admin broo!! ");

    }

}
