package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie findByMoviename(String moviename);

}