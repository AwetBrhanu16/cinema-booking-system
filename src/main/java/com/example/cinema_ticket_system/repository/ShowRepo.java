package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show, Integer> {

}
