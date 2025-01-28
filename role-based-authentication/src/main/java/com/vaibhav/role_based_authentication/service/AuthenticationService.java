package com.vaibhav.role_based_authentication.service;

import com.vaibhav.role_based_authentication.dto.JwtAuthenticationResponse;
import com.vaibhav.role_based_authentication.dto.RefreshTokenRequest;
import com.vaibhav.role_based_authentication.dto.SignUpRequest;
import com.vaibhav.role_based_authentication.dto.SigninRequest;
import com.vaibhav.role_based_authentication.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
