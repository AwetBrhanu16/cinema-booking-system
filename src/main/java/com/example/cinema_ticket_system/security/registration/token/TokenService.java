package com.example.cinema_ticket_system.security.registration.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepo tokenRepo;

    public void saveConfirmationToken(ConfirmationToken token) {
        tokenRepo.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        tokenRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
