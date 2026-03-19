package com.bank.BankingApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @NotNull
    private Integer id;
    private Integer account_number;
    private double balance;
    private Integer user_id;

}
