package com.example.demo.Service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Customers;

@Service
public interface CustomersService {
	
	boolean findCustomer(Customers customer);
	boolean addCustomer(Customers customer);
	List<Customers> getAllCustomers();
	boolean isCustomerExist(Long customerId);
	Customers getCustomerByCustomerId(Long customerId);
	boolean deleteCustomer(Long customerId);
	boolean updateCustomer(Customers customer);
	Customers getCustomerByCredentials(String emailId, String password);

}
