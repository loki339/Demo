package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.Entity.Bank;
import com.example.demo.Service.BankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@RestController
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@PostMapping(value = "/addBank")
	public ResponseEntity<Object> addBank(@RequestBody Bank bank){
		bankService.addBank(bank);
		return new ResponseEntity<Object>("Bank Account Added SuccessFully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/allBanks")
	public ResponseEntity<Object> getAllBanks(){
		ResponseEntity<Object> entity = new ResponseEntity<>(bankService.getAllBanks(), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value= "/getBank/{accountNumber}")  
	public ResponseEntity<Object> getBankByAccountNumber(@PathVariable("accountNumber") String accountNumber)
	{
		Bank bank;
		
		if(bankService.isBankExist(accountNumber)) 
		{
			bank = bankService.getBankByAccNo(accountNumber);
		}
		else {
			bank = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(bank , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteBank/{accountNumber}")
	public ResponseEntity<Object> deleteBank(@PathVariable("accountNumber") String accountNumber)
	{
		boolean flag;
		if(bankService.isBankExist(accountNumber))
		{
			flag = bankService.deleteBank(accountNumber);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateBank/{accountNumber}")
	public ResponseEntity<Object> updateBank(@PathVariable("accountNumber") String accountNumber , @RequestBody Bank bank)
	{
		boolean flag;
		if(bankService.isBankExist(accountNumber))
		{
			flag = bankService.updateBank(bank);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	
	
	@GetMapping(value= "/checkAccountExist/{cardNumber}/{cvvNumber}/{expiryDate}")  
	public ResponseEntity<Object> checkAccountExist(@PathVariable("cardNumber") String cardNumber,@PathVariable("cvvNumber") String cvvNumber,@PathVariable("expiryDate") String expiryDate)
	{
		String expiryDate1 = expiryDate.replace('-', '/');
		Optional<Bank> ob = bankService.findAccount(cardNumber, cvvNumber, expiryDate1);
		boolean flag;
		if(ob.isPresent())
		{ 
			flag = true;
		}else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
}
	