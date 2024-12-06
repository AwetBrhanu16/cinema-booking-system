package com.example.cinema_ticket_system.dto;

import com.example.cinema_ticket_system.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class ShowDto {


    private Long id;
    private Integer theaterId;
    private Integer showId;
    private Time time;
    private Date date;
    private Movie movie;


}
