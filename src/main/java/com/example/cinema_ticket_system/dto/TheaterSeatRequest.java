package com.example.cinema_ticket_system.dto;

import lombok.Data;

@Data
public class TheaterSeatRequest {

    private String address;
    private Integer noOfSeatInRow;
    private Integer noOfPremiumSeat;
    private Integer noOfClassicSeat;
}
