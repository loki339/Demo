package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Bank;
import com.example.demo.Repository.BankRepo;
import com.example.demo.Service.BankService;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	BankRepo bankRepo;

	@Override
	public boolean addBank(Bank bank) {
		bankRepo.save(bank);
		return true;
	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> bankList = bankRepo.findAll();
		return bankList;
	}

	@Override
	public boolean isBankExist(String accountNumber) {
		Optional<Bank> bank = bankRepo.findById(accountNumber);
		if(bank.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Bank getBankByAccNo(String accountNumber) {
		Optional<Bank> bank1 = bankRepo.findById(accountNumber);
		if(bank1.isPresent()) {
			return bank1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteBank(String accountNumber) {
		bankRepo.deleteById(accountNumber);
		return true;
	}

	@Override
	public boolean updateBank(Bank account) {
		Optional<Bank> bank2 = bankRepo.findById(account.getAccountNumber());
		if(bank2.isPresent()) {
			bankRepo.save(account);
			return true;
		}else {
			return false;
		}		
}

	@Override
	public Optional<Bank> findAccount(String cardNumber, String cvvNumber, String expiryDate) {
		
		Optional<Bank>  ob1 = bankRepo.findAccount(cardNumber, cvvNumber, expiryDate);
		
		return ob1;
	}

	@Override
	public Bank findAccountOnCardNumber(String cardNumber) {
		Bank ob1 = bankRepo.findAccountOnCardNumber(cardNumber);
		return ob1;
	}	
	
	
}
