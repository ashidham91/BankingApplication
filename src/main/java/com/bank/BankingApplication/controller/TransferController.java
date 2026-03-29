package com.bank.BankingApplication.controller;


import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.Account;

import com.bank.BankingApplication.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class TransferController {


    @Autowired
    private TransferService transferService;

    @PostMapping("/api/fundTransfer")
    public TransferRequest fundTransfer(@RequestBody TransferRequest transferRequest) {

        transferService.fundTransfer(transferRequest);
        return transferRequest;
    }


    @PostMapping("/api/account")
    public Account saveAccount(@RequestBody Account account) {

        transferService.saveAccount(account);
        return account;
    }

    @GetMapping("/api/accountDetails")
    public List<Account> getAccountList(){

        return transferService.getAccountDetails();
    }
}
