package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.BankTransaction;

public interface BankTransactionRepo  extends JpaRepository<BankTransaction, Long>{

}
