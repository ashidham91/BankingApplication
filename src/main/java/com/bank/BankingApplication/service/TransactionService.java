package com.bank.BankingApplication.service;

import com.bank.BankingApplication.ExceptionHandler.TransactionFailedException;
import com.bank.BankingApplication.dto.TransactionResponse;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Role;
import com.bank.BankingApplication.entity.Transaction;
import com.bank.BankingApplication.repository.TransactionRepository;
import com.bank.BankingApplication.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;
    public TransactionService(TransactionRepository transactionRepository, TransferRepository transferRepository) {
        this.transactionRepository = transactionRepository;
        this.transferRepository = transferRepository;
    }


    public TransactionResponse saveUserTrasaction(TransactionResponse transactionResponse){
        LocalDateTime now = LocalDateTime.now();        // date + time
        Transaction transaction = new Transaction();
        transaction.setId(transactionResponse.getId());
        Double amount;
        Double debitAmount;
        Double criditAmount;
        Account fromAccoutNo=transferRepository.findByAccount(transactionResponse.getFrom_account());
        Account toAccoutNo=transferRepository.findByAccount(transactionResponse.getTo_account());
        if(transactionResponse.getFrom_account().equals(fromAccoutNo.getAccount_number())){

            if(transactionResponse.getTo_account().equals(toAccoutNo.getAccount_number())){

                if(transactionResponse.getAmount() >0 && transactionResponse.getAmount() <= fromAccoutNo.getBalance()){
                    transaction.setTo_account(transactionResponse.getTo_account());
                    transaction.setFrom_account(transactionResponse.getFrom_account());
                    transaction.setAmount(transactionResponse.getAmount());
                    amount =transactionResponse.getAmount();
                    transaction.setDate(now);
                    Transaction transaction1=transactionRepository.save(transaction);
                    debitAmount= fromAccoutNo.getBalance()-amount;
                    transferRepository.updateDebitAccountDetails(fromAccoutNo.getId(),debitAmount);
                    criditAmount = toAccoutNo.getBalance() + amount;
                    transferRepository.updateCreditAccountDetails(toAccoutNo.getId(), criditAmount);
                    transactionRepository.updateStatus(transaction1.getId(),"Success");
                }else{
                    throw new TransactionFailedException("Your Balance is low");
                }
            }else{
                throw new TransactionFailedException("Sender Account Number not Valid");
            }
        }else {
            throw new TransactionFailedException("Account Number not Valid");
        }

        return transactionResponse;
    }


    public List<Transaction> getTranscationdetails(Integer userId,String role){
        List<Transaction> transactionList;

        if(role.equals(Role.ROLE_CUSTOMER.getDisplayName())){
            Account account = transferRepository.findByAccountUserId(userId);
            transactionList = transactionRepository.getTransactionByUser(account.getAccount_number());
        }else{
            transactionList = transactionRepository.findAll();
        }

        return transactionList;
    }
}
