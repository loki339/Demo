package com.example.demo.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AddToCart;

@Service
public interface AddToCartService {

	void addToCart(AddToCart addToCart);
	List<AddToCart> getAllCarts();
	boolean isCartExist(Long cartId);
	AddToCart getCartByCartId(Long cartId);
	boolean deleteCart(Long cartId);
	boolean updateCart(AddToCart cart);
	 List<AddToCart> getCustomerCartById(@Param("customerId") Long customerId);
//	 boolean deleteCartByCustomerIdAndCarId(Long customerId, Long carId);
	 
	 
//	 ...
	 AddToCart getCartByCustomerIdAndCarId(Long customerId, Long carId);
	 boolean deleteCartById(Long cartId);
	 void deleteByCustomerIdCarId(Long customerId, Long carId);


}
