package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.dto.ShowDto;
import com.example.cinema_ticket_system.entities.Show;

public class ShowConverter {

    public static Show showDtoToShow(ShowDto showDto) {

        Show show = Show.builder()
                .showId(showDto.getShowId())
                .time(showDto.getTime())
                .date(showDto.getDate())
                .build();
        return show;
    }
}
