package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Bank;


@Service
public interface BankService {
	
	boolean addBank(Bank bank);
	List<Bank> getAllBanks();
	boolean isBankExist(String accountNumber);
	Bank getBankByAccNo(String accountNumber);
	boolean deleteBank(String accountNumber);
	boolean updateBank(Bank account);
	
	Optional<Bank> findAccount(String cardNumber, String cvvNumber, String expiryDate);
	
	Bank findAccountOnCardNumber(String cardNumber);



}
