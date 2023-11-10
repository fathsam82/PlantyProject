package com.skilldistillery.planty.services;

import com.skilldistillery.planty.entities.User;

public interface AuthService {
	
	public User register(User user);
	
	public User getUserByUsername(String username);
	
	

}
