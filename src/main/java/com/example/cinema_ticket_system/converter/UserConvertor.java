package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.dto.UserRequest;
import com.example.cinema_ticket_system.security.User;


public class UserConvertor {

    public static User userDtoToUser(UserRequest userRequest) {
        User user;
        user = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .username(userRequest.getUsername())
                .mobileNo(userRequest.getMobileNo())
                .email(userRequest.getEmail())
                .userRoles(userRequest.getUserRoles())
                .password(userRequest.getPassword())
                .nonLocked(userRequest.isLocked())
                .enabled(userRequest.isEnabled())
                .build();

        return user;
    }

}
