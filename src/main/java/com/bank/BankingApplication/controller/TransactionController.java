package com.bank.BankingApplication.controller;

import com.bank.BankingApplication.dto.TransactionResponse;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Transaction;
import com.bank.BankingApplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/user/transaction")
    public TransactionResponse login(@RequestBody TransactionResponse transactionResponse) {
        transactionService.saveUserTrasaction(transactionResponse);

        return transactionResponse;
    }

    @GetMapping("/user/transactionHistory")
    public List<Transaction> getTransactionList(){

        List<Transaction> transactionsDetails = transactionService.getTranscationdetails();

        return transactionsDetails;
    }

}
