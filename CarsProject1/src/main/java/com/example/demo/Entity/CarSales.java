package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "carsales")
public class CarSales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleid", nullable = false)
    private Long saleId;

    @Column(name = "transactiondate")
    private Date transactionDate;

    @Column(name = "cost")
    private double  cost;

    @Column(name = "cardnumber", length = 20)
    private String cardNumber;
    
    @Column(name = "shippingaddress", length = 300)
    private String shippingAddress;
    
    @Column(name = "customerid")
    private Long customerId;
    
    @Column(name = "carid")
    private Long carId;
    
    
    
  /*  @ManyToOne
    @JoinColumn(name = "carId")
    private Cars car;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;
*/
	public CarSales() {
		super();
	}



	public CarSales(Long saleId, Date transactionDate, double cost, String cardNumber, String shippingAddress,
			Long customerId, Long carId) {
		super();
		this.saleId = saleId;
		this.transactionDate = transactionDate;
		this.cost = cost;
		this.cardNumber = cardNumber;
		this.shippingAddress = shippingAddress;
		this.customerId = customerId;
		this.carId = carId;
	}



	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	
	public String getShippingAddress() {
		return shippingAddress;
	}



	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}



	@Override
	public String toString() {
		return "CarSales [saleId=" + saleId + ", transactionDate=" + transactionDate + ", cost=" + cost
				+ ", cardNumber=" + cardNumber + ", customerId=" + customerId + ", carId=" + carId + "]";
	}

	
}
