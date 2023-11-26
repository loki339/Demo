package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Bank;
import com.example.demo.Entity.BankTransaction;
import com.example.demo.Repository.BankTransactionRepo;
import com.example.demo.Service.BankTransactionService;

@Service
public class BankTransSericeimpl  implements BankTransactionService {

	@Autowired
	BankTransactionRepo bankTransRepo;
	
	@Override
	public boolean addBankTransaction(BankTransaction bankTransaction) {
		bankTransRepo.save(bankTransaction);
		return true;
	}

	@Override
	public List<BankTransaction> getAllBankTransactions() {
		List<BankTransaction> bankTransList = bankTransRepo.findAll();
		return bankTransList;
	}

	@Override
	public boolean isBankTransactionExist(Long transactionId) {
		Optional<BankTransaction> banktrans = bankTransRepo.findById(transactionId);
		if(banktrans.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public BankTransaction getBankTransactionById(Long transactionId) {
		Optional<BankTransaction> banktrans1 = bankTransRepo.findById(transactionId);
		if(banktrans1.isPresent()) {
			return banktrans1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteBankTransaction(Long transactionId) {
		bankTransRepo.deleteById(transactionId);
		return true;
	}

	@Override
	public boolean updateBankTransaction(BankTransaction bankTransaction) {
		Optional<BankTransaction> banktrans2 = bankTransRepo.findById(bankTransaction.getTransactionId());
		if(banktrans2.isPresent()) {
			bankTransRepo.save(bankTransaction);
			return true;
		}else {
			return false;
		}		
	}
	
}
