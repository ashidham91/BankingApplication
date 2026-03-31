package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.service.AuthService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    // Constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*Authenticates a user based on provided credentials. Returns a token or session information upon successful authentication. Typically, this endpoint is used for user login in web*/
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        return authService.loginService(username, password);
    }

}


