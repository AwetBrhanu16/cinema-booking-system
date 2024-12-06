package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.security.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUsername(String username);


    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.nonLocked = TRUE WHERE a.email = ?1")
    int unlock(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableUser(String username);
}


