package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.AddToCart;
import com.example.demo.Service.AddToCartService;

@RestController
public class AddToCartController {
	
	@Autowired
	AddToCartService addToCartService;
	
	@PostMapping(value = "/addToCart")
	public ResponseEntity<Object> addToCart(@RequestBody AddToCart addToCart){
		addToCartService.addToCart(addToCart);
		return new ResponseEntity<Object>("Cart Added Successfully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallCarts")
	public ResponseEntity<Object> getallCarts(){
		ResponseEntity<Object> entity = new ResponseEntity<>(addToCartService.getAllCarts(), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value= "/getCart/{cartId}")     
	public ResponseEntity<Object> getCartByCartId(@PathVariable("cartId") Long cartId )
	{
		AddToCart addToCart;
		
		if(addToCartService.isCartExist(cartId)) 
		{
			addToCart = addToCartService.getCartByCartId(cartId);
		}
		else {
			addToCart = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(addToCart , HttpStatus.OK);
		return entity;
	}
	
	
	
	@GetMapping(value = "/getCustomerCarts/{customerId}")
	public ResponseEntity<Object> getCustomerCarts(@PathVariable("customerId") Long customerId ){
		ResponseEntity<Object> entity = new ResponseEntity<>(addToCartService.getCustomerCartById(customerId), HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteCart/{cartId}")
	public ResponseEntity<Object> deleteCart(@PathVariable("cartId") Long cartId)
	{
		boolean flag;
		if(addToCartService.isCartExist(cartId))
		{
			flag = addToCartService.deleteCart(cartId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	

	@DeleteMapping("/deleteCartByCustomerIdAndCarId/{customerId}/{carId}")
	public ResponseEntity<Object> deleteCartByCustomerIdAndCarId(
	    @PathVariable("customerId") Long customerId,
	    @PathVariable("carId") Long carId) {

	    AddToCart cart = addToCartService.getCartByCustomerIdAndCarId(customerId, carId);

	    if (cart == null) {
	        return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
	    }

	    boolean deleteResult = addToCartService.deleteCartById(cart.getCartId());

	    if (deleteResult) {
	        return new ResponseEntity<>("Cart Deleted Successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Failed to delete cart", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
	
	@PutMapping( value ="/updateCart/{cartId}")
	public ResponseEntity<Object> updateCart(@PathVariable("cartId") Long cartId , @RequestBody AddToCart addToCart)
	{
		boolean flag;
		if(addToCartService.isCartExist(cartId))
		{
			flag = addToCartService.updateCart(addToCart);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	


}
