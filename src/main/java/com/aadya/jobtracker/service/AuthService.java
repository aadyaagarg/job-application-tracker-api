package com.aadya.jobtracker.service;

import com.aadya.jobtracker.dto.*;
import com.aadya.jobtracker.model.User;
import com.aadya.jobtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;

    public String register(RegisterRequest request){

        User user=new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return "Registered Successfully";
    }

    public String login(LoginRequest request){

        User user=userRepository
                .findByEmail(
                        request.getEmail()
                )
                .orElse(null);

        if(user==null){

            return "User not found";
        }

        if(user.getPassword()
                .equals(
                        request.getPassword()
                )){

            return "Login Successful";
        }

        return "Wrong Password";
    }
}