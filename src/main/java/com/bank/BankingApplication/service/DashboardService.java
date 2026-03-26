package com.bank.BankingApplication.service;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.TransferRepository;
import com.bank.BankingApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferRepository transferRepository;


    public List<TransferRequest> userList(){
        List<TransferRequest> transferRequestList = new ArrayList<>();
        List<Account> userList= transferRepository.findAll();

        for (Account account : userList) {
            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setUser_id(account.getUser_id());
            transferRequest.setBalance(account.getBalance());
            String username = userRepository.getUserName(account.getUser_id());
            transferRequest.setUser_id(account.getUser_id());
            transferRequest.setUserName(username);
            transferRequest.setAccount_number(account.getAccount_number());
            transferRequestList.add(transferRequest);
        }
        return  transferRequestList;
    }
}
