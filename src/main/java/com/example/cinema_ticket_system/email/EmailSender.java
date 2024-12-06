package com.example.cinema_ticket_system.email;

import org.springframework.stereotype.Component;

@Component
public interface EmailSender {

    void send(String to, String email);
}
