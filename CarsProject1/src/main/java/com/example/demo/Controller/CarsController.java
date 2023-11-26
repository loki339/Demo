package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Cars;
import com.example.demo.Service.CarsService;

@RestController
public class CarsController {
	
	@Autowired
	CarsService carsService;
	
	@PostMapping(value = "/addCar")
	public ResponseEntity<Object> addCar(@RequestBody Cars cars){
		carsService.addCar(cars);
		return new ResponseEntity<Object>("Car Added SuccessFully" ,HttpStatus.OK);
	}
	
	@PostMapping(value = "/addNewCar")
	public ResponseEntity<Object> addNewCar(@RequestPart("carImage1") MultipartFile carImage1, @RequestPart("imageName") String imageName){
		
		//System.out.println("ok...." + imageName);
		Random rnd = new Random();
		String filename = imageName; //rnd.nextInt() + carImage1.getOriginalFilename();
		
///////////////
	File fileName;
	try {
		fileName = new ClassPathResource("/").getFile();
	
	String filePath= fileName.getPath().toString();
	int index = filePath.indexOf("\\target");
	System.out.println("index=" + index);
	//filePath.trim("\\target\\classes");
	filePath = filePath.substring(0,index);
	filePath +="/src/main/resources/static/uploads";
	////////////////////
	Path uploadPath = Paths.get(filePath);
	if (!Files.exists(uploadPath)) {
		try {
			Files.createDirectories(uploadPath);
			//System.out.println("path created.");
		} catch (IOException e) {

			e.printStackTrace();
		}
	} else {
		//System.out.println("path exist.");
	}
	try {
		byte[] bytes = carImage1.getBytes();
		Path path = Paths.get(filePath +"/" + filename);
		Files.write(path, bytes);
	} catch (IOException e) {
		e.printStackTrace();
	}
	//cars.setCarImage1(filename);
	//carsService.addCar(cars);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		
		return new ResponseEntity<Object>("Car Added SuccessFully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallCars")
	public ResponseEntity<Object> getallCars(){
		ResponseEntity<Object> entity = new ResponseEntity<>(carsService.getAllCars(), HttpStatus.OK);
		return entity;
	}
	
	@GetMapping(value = "/getCarsByType/{carType}")
	public ResponseEntity<Object> getCarsByType(@PathVariable("carType") String carType){
		ResponseEntity<Object> entity = new ResponseEntity<>(carsService.findByCarType(carType), HttpStatus.OK);
		return entity;
	}
	
	@GetMapping(value = "/getCarsByCustomerId/{customerId}")
	public ResponseEntity<Object> getCarsByCustomerId(@PathVariable("customerId") Long customerId){
		ResponseEntity<Object> entity = new ResponseEntity<>(carsService.findCustomerCarsByCustomerId(customerId), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value = "/getCartCarsByCustomerId/{customerId}")
	public ResponseEntity<Object> getCartCarsByCustomerId(@PathVariable("customerId") Long customerId){
		ResponseEntity<Object> entity = new ResponseEntity<>(carsService.getCustomerCartCarsByCustomerId(customerId), HttpStatus.OK);
		
		
		
		return entity;
	}
	
	
	@GetMapping(value= "/getCar/{carId}")     
	public ResponseEntity<Object> getCarByCarId(@PathVariable("carId") Long carId )
	{
		Cars cars;
		
		if(carsService.isCarExist(carId)) 
		{
			cars = carsService.getCarByCarId(carId);
		}
		else {
			cars = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(cars , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteCar/{carId}")
	public ResponseEntity<Object> deleteCar(@PathVariable("carId") Long carId)
	{
		boolean flag;
		if(carsService.isCarExist(carId))
		{
			flag = carsService.deleteCar(carId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateCar/{carId}")
	public ResponseEntity<Object> updateCar(@PathVariable("carId") Long carId , @RequestBody Cars cars)
	{
		boolean flag;
		if(carsService.isCarExist(carId))
		{
			flag = carsService.updateCar(cars);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	
}
