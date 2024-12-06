package com.example.cinema_ticket_system.security;

import com.example.cinema_ticket_system.converter.UserConvertor;
import com.example.cinema_ticket_system.dto.LoginDto;
import com.example.cinema_ticket_system.dto.UserRequest;
import com.example.cinema_ticket_system.repository.UsersRepo;
import com.example.cinema_ticket_system.security.config.JwtService;
import com.example.cinema_ticket_system.security.registration.token.ConfirmationToken;
import com.example.cinema_ticket_system.security.registration.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService{

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final TokenService tokenService;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Autowired
    private UsersRepo userRepo;

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";


    public String signUpUser(UserRequest userRequest) {

        User userExists = userRepo.findByEmail(userRequest.getEmail());

        if (userExists != null) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }


        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        User convertedUser = UserConvertor.userDtoToUser(userRequest);
        
        userRepo.save(convertedUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                convertedUser
        );


        tokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }


    public List<UserRequest> getAllUsers() {

        List<User> users = userRepo.findAll();

        return users.stream().map(user ->{

            UserRequest userRequest = new UserRequest();

            userRequest.setUsername(user.getUsername());
            userRequest.setFirstname(user.getFirstname());
            userRequest.setLastname(user.getLastname());
            userRequest.setEmail(user.getEmail());
            userRequest.setMobileNo(user.getMobileNo());
            userRequest.setUserRoles(user.getUserRoles());
            userRequest.setPassword(user.getPassword());
            return userRequest;
        }).collect(Collectors.toList());

    }


    public String login(LoginDto loginDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDto.username());
        } else {
            return "fail";
        }
    }

    public int unlock(String email) {
        return userRepo.unlock(email);
    }

    public int enableUser(String username){
        return userRepo.enableUser(username);
    }

}
