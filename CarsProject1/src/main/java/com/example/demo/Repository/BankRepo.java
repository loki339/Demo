package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Bank;
import com.example.demo.Entity.Customers;

public interface BankRepo extends JpaRepository<Bank, String> {
	@Query("SELECT c FROM Bank c WHERE c.cardNumber = :cardNumber AND c.cvvNumber = :cvvNumber AND c.expiryDate= :expiryDate")
	Optional<Bank> findAccount(@Param("cardNumber") String cardNumber, @Param("cvvNumber") String cvvNumber, @Param("expiryDate") String expiryDate);

	@Query("SELECT c FROM Bank c WHERE c.cardNumber = :cardNumber")
	Bank findAccountOnCardNumber(@Param("cardNumber") String cardNumber);

}
