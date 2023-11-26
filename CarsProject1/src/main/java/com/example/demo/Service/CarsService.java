package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Cars;

@Service
public interface CarsService {
	
	void addCar(Cars cars);
	List<Cars> getAllCars();
	boolean isCarExist(Long carId);
	Cars getCarByCarId(Long carId);
	boolean deleteCar(Long carId);
	boolean updateCar(Cars cars);
	List<Cars> findByCarType(String carType);
	List<Cars> findCustomerCarsByCustomerId(Long customerId);
	List<Cars> getCustomerCartCarsByCustomerId(@Param("customerid") Long customerId);

}
