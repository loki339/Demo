package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.AddToCart;
import com.example.demo.Entity.Customers;

public interface AddToCartRepo extends JpaRepository<AddToCart, Long> {
	@Query("SELECT c FROM AddToCart c WHERE c.customerId 	 = :customerId")
    List<AddToCart> getCustomerCartById(@Param("customerId") Long customerId);
	
	@Query("DELETE FROM AddToCart a WHERE a.customerId = :customerId AND a.carId = :carId")
	void deleteCartByCustomerCarIds(@Param("customerId") Long customerId, @Param("carId") Long carId);

	
//	.............countBy
	 AddToCart findByCustomerIdAndCarId(Long customerId, Long carId);
	 void deleteById(Long cartId);
	 
	 @Query("DELETE FROM AddToCart WHERE customerId = :customerId AND carId = :carId")
	 void deleteByCustomerIdCarId(@Param("customerId") Long customerId, @Param("carId") Long carId);

}
