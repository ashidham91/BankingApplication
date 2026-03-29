package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/user/accounts/{userId}/{role}")
    public List<TransferRequest> Account(@PathVariable("userId") Integer userId,@PathVariable("role") String role) {

        List<TransferRequest> userList =dashboardService.userList(userId,role);
        return userList;
    }



}
