package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AddToCart;
import com.example.demo.Entity.Bank;
import com.example.demo.Entity.BankTransaction;
import com.example.demo.Entity.CarSales;
import com.example.demo.Entity.Cars;
import com.example.demo.Entity.Customers;
import com.example.demo.Repository.AddToCartRepo;
import com.example.demo.Repository.BankRepo;
import com.example.demo.Repository.BankTransactionRepo;
import com.example.demo.Repository.CarSalesRepo;
import com.example.demo.Repository.CarsRepository;
import com.example.demo.Repository.CustomersRepo;
import com.example.demo.Service.CarSalesService;
import com.example.demo.Service.EmailService;

@Service
public class CarSalesServiceImpl implements CarSalesService {
	
	@Autowired
	CarSalesRepo carSalesRepo;
	
	@Autowired
	BankTransactionRepo bankTransactionRepo;
	
	@Autowired
	BankRepo bankRepo;
	
	@Autowired
	AddToCartRepo addToCart;
	
	@Autowired
	CarsRepository carsRepo;
	
	@Autowired
	CustomersRepo customerRepo;
	
	@Autowired
	EmailService emailService;
	

	@Override
	public boolean addCarSales(CarSales carSales) {
		
		//add transaction in bank and update balance.
		//admin card no: 1234123412341234
		
		Bank account = bankRepo.findAccountOnCardNumber(carSales.getCardNumber());
		if(account.getBalance() < carSales.getCost()) {
			return false;
		}
		
		carSalesRepo.save(carSales);
		account.setBalance((float) (account.getBalance() - carSales.getCost()) );
		bankRepo.save(account);
		
		BankTransaction trans = new BankTransaction();
		trans.setFromCardNo(carSales.getCardNumber());
		trans.setToCardNo("1234123412341234");
		trans.setTransactionDate(carSales.getTransactionDate());
		trans.setAmount((float)carSales.getCost());
		
		bankTransactionRepo.save(trans);
		
		
		
		
		account = bankRepo.findAccountOnCardNumber("1234123412341234");
		account.setBalance((float) (account.getBalance() + carSales.getCost()) );
		bankRepo.save(account);
		
		System.out.println("cid=" + carSales.getCustomerId() +" carid=" + carSales.getCarId());
		AddToCart cart = addToCart.findByCustomerIdAndCarId(carSales.getCustomerId(), carSales.getCarId());
		
		addToCart.deleteById(cart.getCartId());
		
		//making sold car not available for future purchase
		Cars car = carsRepo.findById(carSales.getCarId()).orElse(null);
		car.setAvailable("no");
		
		carsRepo.save(car);
		///////////////////////
		////Send Mail
		//////////////////////
		
		String subject = "CaronX your purchase receipt.";
		String message = ""
					+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
					+ ""
					+ "Hi!"
					+ "<b>Greating of the day!</b>"
					+ "<p>This is the auto generated receipt of car you purchase in CaronX website.</p>"
					+ "<br>Customer Id: " + carSales.getCustomerId()
					+ "<br><b><u>Purchase Details</b></u>"
					+ carSales.toString()
					+ "<br><b><u>Purchase Details</b></u>"
					+ car.toString()
					+ "</div>";
		
		Customers customer = customerRepo.findById(carSales.getCustomerId()).orElse(null);
		String to = customer.getEmailId();
		
		boolean flag = emailService.sendEmail(subject, message, to);
			
		
		
		return true;
	}

	@Override
	public List<CarSales> getAllCarSales() {
		List<CarSales> salesList = carSalesRepo.findAll();
		return salesList;
	}

	@Override
	public boolean isCarExist(Long saleId) {
		Optional<CarSales> car = carSalesRepo.findById(saleId);
		if(car.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public CarSales getCarBySaleId(Long saleId) {
		Optional<CarSales> car1 = carSalesRepo.findById(saleId);
		if(car1.isPresent()) {
			return car1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteSaleCar(Long saleId) {
		carSalesRepo.deleteById(saleId);
		return true;
	}

	@Override
	public boolean updateSaleCar(CarSales car) {
		Optional<CarSales> car2 = carSalesRepo.findById(car.getSaleId());
		if(car2.isPresent()) {
			carSalesRepo.save(car);
			return true;
		}else {
			return false;
		}		
}	
}