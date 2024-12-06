package com.example.cinema_ticket_system.dto;

import com.example.cinema_ticket_system.enums.Genre;
import com.example.cinema_ticket_system.enums.Language;

import java.util.Date;

public record MovieDto(String moviename,
                       Integer duration,
                       Double rating,
                       Date releaseDate,
                       Genre genre,
                       Language language) {
}
