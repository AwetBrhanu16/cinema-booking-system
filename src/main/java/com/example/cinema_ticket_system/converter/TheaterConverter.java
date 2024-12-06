package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.dto.TheaterRequest;
import com.example.cinema_ticket_system.entities.Theater;


public class TheaterConverter {

    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater
                .builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();

        return theater;
    }
}
