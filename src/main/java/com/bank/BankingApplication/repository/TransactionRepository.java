package com.bank.BankingApplication.repository;

import com.bank.BankingApplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Transaction a SET a.status = :status WHERE a.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);

    @Query("Select  t from Transaction t WHERE t.from_account = :from_account")
    List<Transaction> getTransactionByUser(@Param("from_account") String from_account);

}
