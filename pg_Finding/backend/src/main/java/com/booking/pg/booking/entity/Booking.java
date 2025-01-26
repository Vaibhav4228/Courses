package com.booking.pg.booking.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numOfPerson;


    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numOfPerson=" + numOfPerson +
                ", bookingConformationCode='" + bookingConfirmationCode + '\'' +
                '}';
    }
}

/* my notes -->
* One User can have multiple OrderDetails.
* In the eager loading strategy, if we load the User data, it will also load up all orders associated with it and will store
* it in memory.

However, when we enable lazy loading, Hibernate won’t
* initialize and load OrderDetail data into memory when we pull up a
* UserLazy, until we make an explicit call to it.
*
*  */

