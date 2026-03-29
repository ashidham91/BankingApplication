package com.bank.BankingApplication.service;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Role;
import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.TransferRepository;
import com.bank.BankingApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferRepository transferRepository;


    public List<TransferRequest> userList(Integer userId,String role){
        List<TransferRequest> transferRequestList = new ArrayList<>();
        User user = userRepository.getUserById(userId);
       if(user.getRole().equals(Role.ROLE_CUSTOMER.getDisplayName())) {
           Account userAccount = transferRepository.findByAccountUserId(userId);
           TransferRequest transferRequest = new TransferRequest();
           transferRequest.setUser_id(user.getId());
           transferRequest.setBalance(userAccount.getBalance());
           //String username = userRepository.getUserName(account.getUser_id());
           //transferRequest.setUser_id(account.getUser_id());
           transferRequest.setUserName(user.getUsername());
           transferRequest.setAccount_number(userAccount.getAccount_number());
           transferRequestList.add(transferRequest);

       }else {
           List<Account> userList = transferRepository.findAll();

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
       }
        return  transferRequestList;
    }
}
