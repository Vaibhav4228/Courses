package com.vaibhav_effigo.WebApp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authencticate(String username, String password){
        boolean isValidUserName = username.equalsIgnoreCase("vaibhav");
        boolean isValidPassword = password.equalsIgnoreCase("123");
        return isValidUserName && isValidPassword;


    }
}
