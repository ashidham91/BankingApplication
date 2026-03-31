package com.bank.BankingApplication.controller;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.service.TransferService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class TransferController {


    private final TransferService transferService;

    // Constructor injection
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    /*Creates a new account in the system*/
    @PostMapping("/api/account")
    public Account saveAccount(@RequestBody Account account) {

        transferService.saveAccount(account);
        return account;
    }

    /*Retrieves a list of all accounts in the system. This endpoint returns account details for all users*/
    @GetMapping("/api/accountDetails")
    public List<Account> getAccountList(){

        return transferService.getAccountDetails();
    }
}
