package com.example.cinema_ticket_system.security.registration.authentication;

public record RegistrationRequest(
        String firstname,
        String lastname,
        String username,
        String email,
        String password,
        String role
){

}

