package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
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
	public Optional<OrderCart> getOrderCart(String username, int orderCartId) {
		
		return orderCartRepo.findById(null);
	}

	@Override
	public OrderCart createOrderCart(String username, OrderCart newOrderCart) {
		
		return null;
	}

	@Override
	public OrderCart updateOrderCart(String username, int orderCartId) {
		
		return null;
	}

	@Override
	public boolean deleteOrderCart(String username, int orderCartId) {
		
		return false;
	}

}
