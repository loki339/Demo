package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Cars;
import java.util.List;

public interface CarsRepository extends JpaRepository<Cars, Long> {

	@Query("SELECT c FROM Cars c WHERE c.available = 'yes' and c.carType = :cartype")
	List<Cars> findByCarType(@Param("cartype") String carType);
	
	@Query("SELECT c FROM Cars c WHERE c.customerId = :customerid")
	List<Cars> findCustomerCarsByCustomerId(@Param("customerid") Long customerId);
	
	@Query("SELECT c FROM Cars c WHERE c.carId IN (SELECT d.carId FROM AddToCart d WHERE d.customerId = :customerId)")
	List<Cars> getCustomerCartCarsByCustomerId(@Param("customerId") Long customerId);
	
	
}
