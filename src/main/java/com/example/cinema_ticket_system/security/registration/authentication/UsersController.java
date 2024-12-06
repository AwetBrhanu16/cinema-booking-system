package com.example.cinema_ticket_system.security.registration.authentication;

import com.example.cinema_ticket_system.dto.LoginDto;
import com.example.cinema_ticket_system.dto.UserRequest;
import com.example.cinema_ticket_system.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/get")
    public List<UserRequest> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
