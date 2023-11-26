package com.example.demo.Service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.CarSales;

@Service
public interface CarSalesService {
	
	boolean addCarSales(CarSales carSales);
	List<CarSales> getAllCarSales();
	boolean isCarExist(Long saleId);
	CarSales getCarBySaleId(Long saleId);
	boolean deleteSaleCar(Long saleId);
	boolean updateSaleCar(CarSales car);

}
