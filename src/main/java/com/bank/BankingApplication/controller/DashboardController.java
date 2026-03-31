package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.dto.UserDto;
import com.bank.BankingApplication.service.DashboardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class DashboardController {


    private final DashboardService dashboardService;

    // Constructor injection
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /*Retrieves a list of transfer requests for a specific user filtered by their role. The endpoint uses the userId and role path variables to fetch relevant data from the system.*/
    @GetMapping("/api/accounts/{userId}/{role}")
    public List<TransferRequest> Account(@PathVariable("userId") Integer userId,@PathVariable("role") String role) {

        return dashboardService.userList(userId, role);
    }

    /*Creates a new user in the system*/
    @PostMapping("/api/user")
    public UserDto saveUser(@Valid @RequestBody UserDto userDto) {

        dashboardService.saveUser(userDto);

        return userDto;
    }



}
