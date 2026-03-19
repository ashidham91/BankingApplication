package com.bank.BankingApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @NotNull
    private Integer id;
    private Integer from_account;
    private Integer to_account;
    private Double amount;
    private Date date;
    private String status;
}
