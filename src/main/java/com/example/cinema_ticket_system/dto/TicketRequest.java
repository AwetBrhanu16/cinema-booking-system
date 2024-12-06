package com.example.cinema_ticket_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {

    private Integer showId;
    private Integer userId;
    private List<String> requestSeats;

}
