package com.booking.pg.booking.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String Phone;
    private String role;
    private List<BookingDTO> bookings = new ArrayList<>();
}
