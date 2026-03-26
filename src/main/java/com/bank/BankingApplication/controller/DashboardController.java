package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.LoginRequest;
import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.UserRepository;
import com.bank.BankingApplication.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class DashboardController {

    @Autowired
    DashboardService dashboardService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test")
    public String testData(){

        return "testing successfully";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile() {
        return "Logged in user";
    }


    @GetMapping("/user/accounts")
    public List<TransferRequest> Account() {

        List<TransferRequest> userList =dashboardService.userList();
        return userList;
    }



}
