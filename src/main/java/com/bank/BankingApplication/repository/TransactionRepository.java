package com.bank.BankingApplication.repository;

import com.bank.BankingApplication.entity.Transaction;
import com.bank.BankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Transaction a SET a.status = :status WHERE a.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);
}
