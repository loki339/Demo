package com.example.demo.ServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Cars;
import com.example.demo.Entity.Customers;
import com.example.demo.Repository.CarsRepository;
import com.example.demo.Service.CarsService;

@Service
public class CarsSericeImpl implements CarsService {

	@Autowired
	CarsRepository carsRepository;
	
	@Override
	public void addCar(Cars cars) {
		carsRepository.save(cars);
	}

	@Override
	public List<Cars> getAllCars() {
		List<Cars> carList = carsRepository.findAll();
		return carList;
	}

	@Override
	public boolean isCarExist(Long carId) {
		Optional<Cars> car = carsRepository.findById(carId);
		if(car.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Cars getCarByCarId(Long carId) {
		Optional<Cars> car1 = carsRepository.findById(carId);
		if(car1.isPresent()) {
			return car1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteCar(Long carId) {
		carsRepository.deleteById(carId);
		return true;
	}

	@Override
	public boolean updateCar(Cars cars) {
		Optional<Cars> car2 = carsRepository.findById(cars.getCarId());
		if(car2.isPresent()) {
			carsRepository.save(cars);
			return true;
		}else {
			return false;
		}		
}

	@Override
	public List<Cars> findByCarType(String carType) {
		List<Cars> cars = carsRepository.findByCarType(carType);
	    return cars;
		
	}

	@Override
	public List<Cars> findCustomerCarsByCustomerId(Long customerId) {
		List<Cars> cars = carsRepository.findCustomerCarsByCustomerId(customerId);
		return cars;
	}

	@Override
	public List<Cars> getCustomerCartCarsByCustomerId(Long customerId) {
		List<Cars> cars = carsRepository.getCustomerCartCarsByCustomerId(customerId);
		return cars;
	}	
}