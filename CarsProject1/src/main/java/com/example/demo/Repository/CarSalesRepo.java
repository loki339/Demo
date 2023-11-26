package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.CarSales;

public interface CarSalesRepo extends JpaRepository<CarSales, Long> {
	

}
