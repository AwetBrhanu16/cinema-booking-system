package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.dto.MovieDto;
import com.example.cinema_ticket_system.entities.Movie;
import com.example.cinema_ticket_system.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public String addMovie(Movie movie) {
        Movie myMovie = movieRepo.findByMoviename(movie.getMoviename());

        if (myMovie != null && myMovie.getLanguage().equals(movie.getLanguage())) {
            throw new RuntimeException("movie already exists");
        }

        assert myMovie != null;
        Movie
                .builder()
                .moviename(myMovie.getMoviename())
                .duration(myMovie.getDuration())
                .rating(myMovie.getRating())
                .releaseDate(myMovie.getReleaseDate())
                .genre(myMovie.getGenre())
                .language(myMovie.getLanguage())
                .build();
        movieRepo.save(movie);

        return "Movie added successfully";
    }
    public List<MovieDto> getAllMovies() {
        return movieRepo.findAll().stream().map(movie ->
             new MovieDto(
                     movie.getMoviename(),
                     movie.getDuration(),
                     movie.getRating(),
                     movie.getReleaseDate(),
                     movie.getGenre(),
                     movie.getLanguage()
             )).collect(Collectors.toList());
         }

    }
