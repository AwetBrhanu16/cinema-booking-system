package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.dto.ShowDto;
import com.example.cinema_ticket_system.dto.ShowSeatRequest;
import com.example.cinema_ticket_system.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto) {

        String show = showService.addShow(showDto);

        return new ResponseEntity<>(show, HttpStatus.CREATED);
    }

    @PostMapping("addAssociateShowSeats")
    public ResponseEntity<String> addAssociateShowSeats(@RequestBody ShowSeatRequest showSeatRequest) {
        String showSeat = showService.associateShowSeats(showSeatRequest);
        return new ResponseEntity<>(showSeat, HttpStatus.CREATED);
    }

    @GetMapping("/getShows")
    public List<ShowDto> getShow(){
        return showService.getAllShows();
    }

}
