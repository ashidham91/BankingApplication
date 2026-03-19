package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.UserRepository;
import com.bank.BankingApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        User log = userRepository.findByUsername(username);
        if(log !=null) {

            if (log.getUsername().equals(username) && log.getPassword().equals(password)) {
                return jwtUtil.generateToken(username);
            }
        }
        throw new RuntimeException("Invalid credentials");

    }




}


