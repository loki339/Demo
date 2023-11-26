package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customers;
import com.example.demo.Repository.CustomersRepo;
import com.example.demo.Service.CustomersService;

@Service
public class CustomerServiceImpl implements CustomersService {

	@Autowired
	CustomersRepo customersRepo;
	
	@Override
	public Customers getCustomerByCredentials(String emailId, String password) {
	    Optional<Customers> customer = customersRepo.findByEmailAndPass(emailId, password);
	    return customer.orElse(null);
}
	
	@Override
	public boolean findCustomer(Customers customer) {
	    Optional<Customers> customerObj = customersRepo.findByEmail(customer.getEmailId());

	    if (customerObj.isPresent()) {
	        Customers obj = customerObj.get();
	        String dbPass = obj.getPassword();

	        if (dbPass.equals(customer.getPassword())) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    return false;
	}

	
	@Override
	public boolean addCustomer(Customers customer) {
		customersRepo.save(customer);
		return true;
	}

	@Override
	public List<Customers> getAllCustomers() {
		List<Customers> customersList = customersRepo.findAll();
		return customersList;
	}

	@Override
	public boolean isCustomerExist(Long customerId) {
		Optional<Customers> customer = customersRepo.findById(customerId);
		if(customer.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Customers getCustomerByCustomerId(Long customerId) 
	{
		Optional<Customers> customer1 = customersRepo.findById(customerId);
		if(customer1.isPresent()) {
			return customer1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteCustomer(Long customerId) {
		customersRepo.deleteById(customerId);
		return true;
	}

	@Override
	public boolean updateCustomer(Customers customer) {
		Optional<Customers> customer2 = customersRepo.findById(customer.getCustomerId());
		if(customer2.isPresent()) {
			customersRepo.save(customer);
			return true;
		}else {
			return false;
		}		
}
}
