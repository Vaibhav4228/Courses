package com.vaibhav.role_based_authentication.service.impl;


import com.vaibhav.role_based_authentication.dto.JwtAuthenticationResponse;
import com.vaibhav.role_based_authentication.dto.RefreshTokenRequest;
import com.vaibhav.role_based_authentication.dto.SignUpRequest;
import com.vaibhav.role_based_authentication.dto.SigninRequest;
import com.vaibhav.role_based_authentication.entity.Role;
import com.vaibhav.role_based_authentication.entity.User;
import com.vaibhav.role_based_authentication.repo.UserRepo;
import com.vaibhav.role_based_authentication.service.AuthenticationService;
import com.vaibhav.role_based_authentication.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService  jwtService;

    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }



    public User signup(SignUpRequest signUpRequest){
        User user = new User();

        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepo.save(user);
    }
    public JwtAuthenticationResponse signIn(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var user = userRepo.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("invalid email bro"));
        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
        }

        public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepo.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
           var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
        }
    }

