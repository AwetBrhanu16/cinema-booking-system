package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.dto.MovieDto;
import com.example.cinema_ticket_system.entities.Movie;
import com.example.cinema_ticket_system.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
      String result = movieService.addMovie(movie);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getMovies")
    public List<MovieDto> getAllMovies(){
       return movieService.getAllMovies();
    }
}