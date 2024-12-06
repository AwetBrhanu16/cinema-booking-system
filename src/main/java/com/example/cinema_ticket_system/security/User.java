package com.example.cinema_ticket_system.security;

import com.example.cinema_ticket_system.entities.Ticket;
import com.example.cinema_ticket_system.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;
    
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String username;

    private String password;

    private String mobileNo;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();

    private Boolean nonLocked = false;

    private Boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
