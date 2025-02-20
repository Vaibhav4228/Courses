package com.effigo.vaibhav.projections_views.Controller;


import com.effigo.vaibhav.projections_views.DTOs.UserDTO;
import com.effigo.vaibhav.projections_views.Service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getPaginatedUsers((org.springframework.data.domain.Pageable) pageable));
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("User created successfully!");
    }
}

