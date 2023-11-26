package com.example.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId", nullable = false)
    private Long carId;

    @Column(name = "postdate")
    private Date postDate;

    @Column(name = "cartype", nullable = false, length = 30)
    private String carType;

    @Column(name = "company", length = 100)
    private String company;

    @Column(name = "modelname", length = 100)
    private String modelName;

    @Column(name = "yearmodel")
    private Integer yearModel;

    @Column(name = "fueltype", length = 50)
    private String fuelType;

    @Column(name = "cardescription", length = 300)
    private String carDescription;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "carimage")
    private String carImage1;

    @Column(name = "available", length = 50)
    private String available;
    
    @Column(name = "customerid")
    private Long customerId;

   /* @ManyToOne
    @JoinColumn(name = "customerid")
    private Customers customer;
    
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<CarSales> carSales;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<AddToCart> carts;
    */
	public Cars() {
		super();
	}

public Cars(Long carId, Date postDate, String carType, String company, String modelName, Integer yearModel,
		String fuelType, String carDescription, Float cost, String carImage1, String available, Long customerId) {
	super();
	this.carId = carId;
	this.postDate = postDate;
	this.carType = carType;
	this.company = company;
	this.modelName = modelName;
	this.yearModel = yearModel;
	this.fuelType = fuelType;
	this.carDescription = carDescription;
	this.cost = cost;
	this.carImage1 = carImage1;
	this.available = available;
	this.customerId = customerId;
}

public Long getCarId() {
	return carId;
}

public void setCarId(Long carId) {
	this.carId = carId;
}

public Date getPostDate() {
	return postDate;
}

public void setPostDate(Date postDate) {
	this.postDate = postDate;
}

public String getCarType() {
	return carType;
}

public void setCarType(String carType) {
	this.carType = carType;
}

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}

public String getModelName() {
	return modelName;
}

public void setModelName(String modelName) {
	this.modelName = modelName;
}

public Integer getYearModel() {
	return yearModel;
}

public void setYearModel(Integer yearModel) {
	this.yearModel = yearModel;
}

public String getFuelType() {
	return fuelType;
}

public void setFuelType(String fuelType) {
	this.fuelType = fuelType;
}

public String getCarDescription() {
	return carDescription;
}

public void setCarDescription(String carDescription) {
	this.carDescription = carDescription;
}

public Float getCost() {
	return cost;
}

public void setCost(Float cost) {
	this.cost = cost;
}

public String getCarImage1() {
	return carImage1;
}

public void setCarImage1(String carImage1) {
	this.carImage1 = carImage1;
}

public String getAvailable() {
	return available;
}

public void setAvailable(String available) {
	this.available = available;
}

public Long getCustomerId() {
	return customerId;
}

public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}

@Override
public String toString() {
	return "Cars [carId=" + carId + ", postDate=" + postDate + ", carType=" + carType + ", company=" + company
			+ ", modelName=" + modelName + ", yearModel=" + yearModel + ", fuelType=" + fuelType + ", carDescription="
			+ carDescription + ", cost=" + cost + ", carImage1=" + carImage1 + ", available=" + available
			+ ", customerId=" + customerId + "]";
}

	

	
}