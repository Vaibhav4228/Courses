package com.project.Mappers.Controller;

import com.project.Mappers.Entity.UserEntity;
import com.project.Mappers.Service.JWTUtils;
import com.project.Mappers.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JWTUtils jwtUtils;

    public AuthController(UserService userService, JWTUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserEntity userRequest) {
        try {
            UserEntity newUser = userService.signUp(userRequest.getUsername(), userRequest.getPassword());
            return ResponseEntity.ok("User created with ID: " + newUser.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userRequest) {
        try {
            UserEntity user = userService.login(userRequest.getUsername(), userRequest.getPassword());
            String token = jwtUtils.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}