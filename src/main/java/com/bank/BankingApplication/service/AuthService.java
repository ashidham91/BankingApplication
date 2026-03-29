package com.bank.BankingApplication.service;

import com.bank.BankingApplication.repository.UserRepository;
import com.bank.BankingApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public  String loginService(String username,String password){
        com.bank.BankingApplication.entity.User log = userRepository.findByUsername(username);
        if(log !=null) {
            String encoded =log.getPassword();
            boolean isMatch = passwordEncoder.matches(password,encoded);
           if(isMatch) {
               if (log.getUsername().equals(username)) {
                   return jwtUtil.generateToken(username, log.getId(), log.getRole());
               }
           }
        }
        throw new RuntimeException("Invalid credentials");

    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.bank.BankingApplication.entity.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

}
