package com.bank.BankingApplication.service;

import com.bank.BankingApplication.dto.TransferRequest;
import com.bank.BankingApplication.dto.UserDto;
import com.bank.BankingApplication.entity.Account;
import com.bank.BankingApplication.entity.Role;
import com.bank.BankingApplication.entity.User;
import com.bank.BankingApplication.repository.TransferRepository;
import com.bank.BankingApplication.repository.UserRepository;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {


    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    private final PasswordEncoder passwordEncoder;

    public DashboardService(UserRepository userRepository, TransferRepository transferRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
        this.passwordEncoder = passwordEncoder;
    }




    public List<TransferRequest> userList(Integer userId,String role){
        List<TransferRequest> transferRequestList = new ArrayList<>();
        User user = userRepository.getUserById(userId);
       if(role.equals(Role.ROLE_CUSTOMER.getDisplayName())) {
           Account userAccount = transferRepository.findByAccountUserId(userId);
           TransferRequest transferRequest = new TransferRequest();
           transferRequest.setUser_id(user.getId());
           transferRequest.setBalance(userAccount.getBalance());
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


    public UserDto saveUser(UserDto userDto){

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // 🔐 Encrypt password
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(userDto.getRole());
        userRepository.save(user);

        return userDto;
    }
}
