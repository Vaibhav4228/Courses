package com.booking.pg.booking.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Wait!!! email is required ")
    private String email;
    private String password;

}
