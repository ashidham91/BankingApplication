package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.TransactionResponse;
import com.bank.BankingApplication.entity.Transaction;

import com.bank.BankingApplication.service.TransactionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {


    private final TransactionService transactionService;

    // Constructor injection
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /*Creates a new transaction record in the system*/
    @PostMapping("/api/transaction")
    public TransactionResponse saveTrasaction(@RequestBody TransactionResponse transactionResponse) {
        transactionService.saveUserTrasaction(transactionResponse);

        return transactionResponse;
    }

    /*Retrieves the transaction history for a specific user, filtered by their role*/
    @GetMapping("/api/transactionHistory/{userId}/{role}")
    public List<Transaction> getTransactionList(@PathVariable Integer userId,@PathVariable String role){

        return transactionService.getTranscationdetails(userId,role);
    }

}
