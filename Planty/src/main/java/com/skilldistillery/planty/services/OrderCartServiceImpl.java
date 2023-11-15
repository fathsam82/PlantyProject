package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.OrderCartRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class OrderCartServiceImpl implements OrderCartService {
	
	@Autowired
	private OrderCartRepository orderCartRepo;
	
	@Autowired UserRepository userRepo;

	@Override
	public List<OrderCart> listAllOrderCarts(String username) {
		
		return orderCartRepo.findByUser_Username(username);
	}

	@Override
	public OrderCart getOrderCart(String username, int orderCartId) {
		OrderCart orderCart = null;
		
		Optional<OrderCart> orderCartOpt = orderCartRepo.findById(orderCartId);
		if (orderCartOpt.isPresent() && orderCartOpt.get().getUser().getUsername().equals(username)) {
			orderCart = orderCartOpt.get();
		}
		return orderCart;
	}

	@Override
	public OrderCart createOrderCart(String username, OrderCart newOrderCart) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			newOrderCart.setUser(user);
			return orderCartRepo.saveAndFlush(newOrderCart);
		}
		return null;
	}

	@Override
	public OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
		OrderCart existing = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if(existing != null) {
			
			existing.setPaymentMethod(updatedOrderCart.getPaymentMethod());
			orderCartRepo.saveAndFlush(existing);
			
		}
		return existing;
	}

	@Override
	public boolean deleteOrderCart(String username, int orderCartId) {
		
		return false;
	}
	
	
	
}
