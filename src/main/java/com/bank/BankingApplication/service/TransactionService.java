package com.bank.BankingApplication.service;

import com.bank.BankingApplication.dto.TransactionResponse;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Transaction;
import com.bank.BankingApplication.repository.TransactionRepository;
import com.bank.BankingApplication.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransferRepository transferRepository;

    public TransactionResponse saveUserTrasaction(TransactionResponse transactionResponse){
        LocalDateTime now = LocalDateTime.now();        // date + time
        Transaction transaction = new Transaction();
        transaction.setId(transactionResponse.getId());
        Double amount = 0.0;
        Double debitAmount =0.0;
        Double criditAmount = 0.0;
        Account fromAccoutNo=transferRepository.findByAccount(transactionResponse.getFrom_account());
        if(transactionResponse.getFrom_account() == fromAccoutNo.getAccount_number()){
            transaction.setFrom_account(transactionResponse.getFrom_account());
        }
        else {
            transactionResponse.setStatus("Invalid Account number");
        }
        Account toAccoutNo=transferRepository.findByAccount(transactionResponse.getTo_account());

        if(transactionResponse.getTo_account() == toAccoutNo.getAccount_number()){
            transaction.setTo_account(transactionResponse.getTo_account());
        }else {
            transactionResponse.setStatus("Invalid Account number");
        }

        if(fromAccoutNo.getBalance() >0){
            transaction.setAmount(transactionResponse.getAmount());
            amount =transactionResponse.getAmount();
        }
        //transaction.setStatus(transactionResponse.getStatus());
        transaction.setDate(now);
        //transaction.setStatus("Success");
        Transaction transaction1=transactionRepository.save(transaction);
        debitAmount= fromAccoutNo.getBalance()-amount;
        transferRepository.updateDebitAccountDetails(fromAccoutNo.getId(),debitAmount);
        criditAmount = toAccoutNo.getBalance() + amount;
        transferRepository.updateCreditAccountDetails(toAccoutNo.getId(), criditAmount);
        transactionRepository.updateStatus(transaction1.getId(),"Success");
        return transactionResponse;
    }


    public List<Transaction> getTranscationdetails(){

        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }
}
