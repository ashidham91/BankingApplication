package com.bank.BankingApplication.service;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    public TransferRequest fundTransfer(TransferRequest transferRequest){

        Account account = new Account();
        transferRequest.setAccount_number(account.getAccount_number());
        transferRequest.setBalance(account.getBalance());
        transferRequest.setUser_id(account.getUser_id());
        transferRepository.save(account);
        return transferRequest;
    }

    public  List<Account> getAccountDetails(){

        List<Account> acc =transferRepository.findAll();

        return acc;
    }
}
