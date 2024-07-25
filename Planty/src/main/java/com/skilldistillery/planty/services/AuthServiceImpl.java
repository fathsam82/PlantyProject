package com.skilldistillery.planty.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.OrderCartRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private OrderCartRepository orderCartRepo;

	@Override
	public User register(User user) {
		user.setEnabled(true);
		user.setRole("standard");
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		User savedUser = userRepo.saveAndFlush(user);
		
		OrderCart orderCart = new OrderCart();
		orderCart.setUser(savedUser);
		orderCartRepo.saveAndFlush(orderCart);
		
		return savedUser;
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		return userOpt.orElseThrow(() ->
		new EntityNotFoundException("User not found for " + username)
		);
		
	

	}

}
