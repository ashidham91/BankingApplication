package com.bank.BankingApplication.repository;

import com.bank.BankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransferRepository extends JpaRepository<Account,Integer> {

     @Query("SELECT a FROM Account a WHERE a.account_number = :account_number")
     Account findByAccount(@Param("account_number") Integer account_number);
     @Query("SELECT a FROM Account a WHERE a.user_id = :user_id ORDER BY a.id DESC")
     Account findByAccountUserId(@Param("user_id") Integer user_id);




     @Modifying
     @Transactional
     @Query("UPDATE Account a SET a.balance = :debitAmount WHERE a.id = :id")
     Double updateDebitAccountDetails(@Param("id") Integer id, @Param("debitAmount") Double debitAmount);

     @Modifying
     @Transactional
     @Query("UPDATE Account a SET a.balance = :criditAmount WHERE a.id = :id")
     Double updateCreditAccountDetails(@Param("id") Integer id, @Param("criditAmount") Double criditAmount);
}
