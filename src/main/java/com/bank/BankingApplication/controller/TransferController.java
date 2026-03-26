package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.TransactionResponse;
import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.service.TransactionService;
import com.bank.BankingApplication.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class TransferController {


    @Autowired
    TransferService transferService;

    @PostMapping("/user/transfer")
    public TransferRequest fundTransfer(@RequestBody TransferRequest transferRequest) {

        transferService.fundTransfer(transferRequest);
        return transferRequest;
    }


    @GetMapping("/user/account")
    public List<Account> getAccountList(){

        List<Account> accountdetails = transferService.getAccountDetails();

        return accountdetails;
    }
}
