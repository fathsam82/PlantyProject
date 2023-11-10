package com.skilldistillery.planty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public User register(User user) {
		user.setEnabled(true);
		user.setRole("standard");
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		userRepo.saveAndFlush(user);
		
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		
	
		return userRepo.findByUsername(username);
	}

}
