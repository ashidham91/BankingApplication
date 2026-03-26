package com.bank.BankingApplication.repository;

import com.bank.BankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);


    @Query("Select u.username from User u WHERE u.id = :id")
    String getUserName(@Param("id") Integer id);


}
