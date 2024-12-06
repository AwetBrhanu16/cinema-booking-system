package com.example.cinema_ticket_system.dto;

import lombok.Data;

@Data
public class ShowSeatRequest {

    private Integer showId;
    private Integer priceOfPremiumSeat;
    private Integer priceOfClassicSeat;

}
