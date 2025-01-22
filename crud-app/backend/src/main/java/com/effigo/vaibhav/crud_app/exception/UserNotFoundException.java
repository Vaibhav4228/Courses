package com.effigo.vaibhav.crud_app.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("user not founnd the user woth id "+ id);
    }
}

