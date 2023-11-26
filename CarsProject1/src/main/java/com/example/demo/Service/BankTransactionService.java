package com.example.demo.Service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.BankTransaction;

@Service
public interface BankTransactionService
{
    boolean addBankTransaction(BankTransaction bankTransaction);
    List<BankTransaction> getAllBankTransactions();
	boolean isBankTransactionExist(Long transactionId);
    BankTransaction getBankTransactionById(Long transactionId);
    boolean deleteBankTransaction(Long transactionId);
    boolean updateBankTransaction(BankTransaction bankTransaction);
    
}
