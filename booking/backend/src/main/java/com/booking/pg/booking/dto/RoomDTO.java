package com.booking.pg.booking.dto;

import com.booking.pg.booking.entity.Booking;
import lombok.Data;

import java.util.List;

@Data
public class RoomDTO {
    private Long id;
    private String roomType;
    private String roomPic;
    private String roomPrice;
    private String roomDesc;
    private List<BookingDTO> bookings;



}
