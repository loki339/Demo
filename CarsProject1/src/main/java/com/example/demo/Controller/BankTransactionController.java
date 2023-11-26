package com.example.demo.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.Entity.BankTransaction;
import com.example.demo.Service.BankTransactionService;
import jakarta.servlet.http.HttpSession;

@RestController
public class BankTransactionController {

	@Autowired
	BankTransactionService bankTransService;
	
	@PostMapping(value = "/addBankTransaction")
	public ResponseEntity<Object> addBankTransaction(@RequestBody BankTransaction bankTransaction){
		bankTransService.addBankTransaction(bankTransaction);
		return new ResponseEntity<Object>("Transaction Added SuccessFully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/allBankTransactions")
	public ResponseEntity<Object> getAllBankTransactions(){
		ResponseEntity<Object> entity = new ResponseEntity<>(bankTransService.getAllBankTransactions(), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value= "/getBankTransaction/{transactionId}")    
	public ResponseEntity<Object> getBankTransByTransactionId(@PathVariable("transactionId") Long transactionId)
	{
		BankTransaction banktransaction;
		
		if(bankTransService.isBankTransactionExist(transactionId)) 
		{
			banktransaction = bankTransService.getBankTransactionById(transactionId);
		}
		else {
			banktransaction = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(banktransaction , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteBankTransaction/{transactionId}")
	public ResponseEntity<Object> deleteBankTransaction(@PathVariable("transactionId") Long transactionId)
	{
		boolean flag;
		if(bankTransService.isBankTransactionExist(transactionId))
		{
			flag = bankTransService.deleteBankTransaction(transactionId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateBankTransaction/{transactionId}")
	public ResponseEntity<Object> updateBank(@PathVariable("transactionId") Long transactionId , @RequestBody BankTransaction bankTransaction)
	{
		boolean flag;
		if(bankTransService.isBankTransactionExist(transactionId))
		{
			flag = bankTransService.updateBankTransaction(bankTransaction);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	
}