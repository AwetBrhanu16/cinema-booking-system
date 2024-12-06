package com.example.cinema_ticket_system.dto;

import com.example.cinema_ticket_system.enums.Gender;
import com.example.cinema_ticket_system.enums.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserRequest {

    private String firstname;
    private String lastname;
    private String username;
    private Integer age;
    private String address;
    private String mobileNo;
    private String email;
    private Gender gender;
    private boolean locked;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    private String password;

    public UserRequest(String firstname,
                   String lastname,
                   String username,
                   String email,
                   String password,
                   UserRoles userRoles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username= username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }

}