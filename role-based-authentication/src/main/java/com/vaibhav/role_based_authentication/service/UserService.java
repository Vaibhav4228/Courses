package com.vaibhav.role_based_authentication.service;

import com.vaibhav.role_based_authentication.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserService {


    UserDetailsService userDetailsService();
}


