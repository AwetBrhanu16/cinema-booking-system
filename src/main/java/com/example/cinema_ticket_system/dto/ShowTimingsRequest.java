package com.example.cinema_ticket_system.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ShowTimingsRequest {

    private Date date;
    private Integer theaterId;
    private Integer movieId;
}
