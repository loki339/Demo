package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "addtocart")
public class AddToCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid", nullable = false)
    private Long cartId;

    @Column(name = "customerId", nullable = false)
    private Long customerId;

    @Column(name = "carid", nullable = false)
    private Long carId;

    public AddToCart() {
        super();
    }

	public AddToCart(Long cartId, Long customerId, Long carId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.carId = carId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
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

	@Override
	public String toString() {
		return "AddToCart [cartId=" + cartId + ", customerId=" + customerId + ", carId=" + carId + "]";
	}
    
    

    
}
