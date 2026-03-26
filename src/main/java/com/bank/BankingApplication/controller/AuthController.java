package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.UserRepository;
import com.bank.BankingApplication.service.AuthService;
import com.bank.BankingApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        String tokenData = authService.loginService(username, password);
        return tokenData;
    }

}


