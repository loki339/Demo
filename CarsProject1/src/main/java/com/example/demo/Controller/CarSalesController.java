	package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Bank;
import com.example.demo.Entity.CarSales;
import com.example.demo.Service.BankService;
import com.example.demo.Service.CarSalesService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CarSalesController {
	
	@Autowired
	CarSalesService carSalesService;
	
	@Autowired
	BankService bankService;
	
	@PostMapping(value = "/addCarSale")
	public ResponseEntity<Object> addCarSale(@RequestBody CarSales carSales,@RequestParam("cvvNumber") String cvvNumber, @RequestParam("expiryDate") String expiryDate){
		System.out.println("aaa=" + carSales);
		System.out.println("carnunber:"+ carSales.getCardNumber() +", "+ cvvNumber +", " + expiryDate);
		String expiryDate1 = expiryDate.replace('-', '/');
		Optional<Bank> ob = bankService.findAccount(""+carSales.getCardNumber(), cvvNumber, expiryDate1);
		boolean flag;
		if(ob.isPresent())
		{ 
			carSalesService.addCarSales(carSales);
			
			flag = true;
		}else {
			flag = false;
		}
		System.out.println("flag=" + flag);
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallCarSales")
	public ResponseEntity<Object> getAllCarSales(){
		ResponseEntity<Object> entity = new ResponseEntity<>(carSalesService.getAllCarSales(), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value= "/getCarSale/{saleId}")    
	public ResponseEntity<Object> getSaleCarBySaleId(@PathVariable("saleId") Long saleId)
	{
		CarSales carsales;
		
		if(carSalesService.isCarExist(saleId)) 
		{
			carsales = carSalesService.getCarBySaleId(saleId);
		}
		else {
			carsales = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(carsales , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteSaleCar/{saleId}")
	public ResponseEntity<Object> deleteSaleCar(@PathVariable("saleId") Long saleId)
	{
		boolean flag;
		if(carSalesService.isCarExist(saleId))
		{
			flag = carSalesService.deleteSaleCar(saleId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateSaleCar/{saleId}")
	public ResponseEntity<Object> updateBank(@PathVariable("saleId") Long saleId , @RequestBody CarSales carSales)
	{
		boolean flag;
		if(carSalesService.isCarExist(saleId))
		{
			flag = carSalesService.updateSaleCar(carSales);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	
}

	


