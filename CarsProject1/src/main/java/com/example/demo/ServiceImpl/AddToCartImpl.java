package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.AddToCart;
import com.example.demo.Repository.AddToCartRepo;
import com.example.demo.Service.AddToCartService;

@Service
public class AddToCartImpl implements AddToCartService {

	@Autowired
	AddToCartRepo addToCartRepo;
	
	@Override
	public void addToCart(AddToCart addToCart) {
		
		AddToCart ob = getCartByCustomerIdAndCarId(addToCart.getCustomerId(), addToCart.getCarId());
		if(ob == null) {
			addToCartRepo.save(addToCart);
		}
	}

	@Override
	public List<AddToCart> getAllCarts() {
		List<AddToCart> addToCartList = addToCartRepo.findAll();
		return addToCartList;
	}

	@Override
	public boolean isCartExist(Long cartId) {
		Optional<AddToCart> addToCart = addToCartRepo.findById(cartId);
		if(addToCart.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public AddToCart getCartByCartId(Long cartId) {
		Optional<AddToCart> addToCart = addToCartRepo.findById(cartId);
		if(addToCart.isPresent()) {
			return addToCart.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteCart(Long cartId) {
		addToCartRepo.deleteById(cartId);
		return true;
	}

	@Override
	public boolean updateCart(AddToCart cart) {
		Optional<AddToCart> addToCart = addToCartRepo.findById(cart.getCartId());
		if(addToCart.isPresent()) {
			addToCartRepo.save(cart);
			return true;
		}else {
			return false;
		}		
}

	@Override
	public List<AddToCart> getCustomerCartById(Long customerId) {
		
		List<AddToCart> carts = addToCartRepo.getCustomerCartById(customerId);
		return carts;
	}

//	@Override
//	public boolean deleteCartByCustomerIdAndCarId(Long customerId, Long carId) {
//	    try {
//	        addToCartRepo.deleteCartByCustomerCarIds(customerId, carId);
//	        return true;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}

	 @Override
	    public AddToCart getCartByCustomerIdAndCarId(Long customerId, Long carId) {
	        return addToCartRepo.findByCustomerIdAndCarId(customerId, carId);

	    }

	    @Override
	    public boolean deleteCartById(Long cartId) {
	        try {
	            addToCartRepo.deleteById(cartId);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

		@Override
		public void deleteByCustomerIdCarId(Long customerId, Long carId) {
			// TODO Auto-generated method stub
			addToCartRepo.deleteByCustomerIdCarId(customerId, carId);
		}
}
