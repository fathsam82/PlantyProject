package com.skilldistillery.planty.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.planty.entities.ShippingAddress;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.ShippingAddressRepository;
import com.skilldistillery.planty.repositories.UserRepository;

public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepo;

	@Override
	public List<ShippingAddress> listShippingAddressForLoggedInUser(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException("User not found for username " + username);
		}
		List<ShippingAddress> shippingAddresses = user.getShippingAddresses();
		if (shippingAddresses == null) {
			return new ArrayList<>();
		}
		return shippingAddresses;
	}

	@Override
	public ShippingAddress getShippingAddress(int shippingAddressId, String username) {
		
		return shippingAddressRepo.;
	}

	@Override
	public ShippingAddress createShippingAddress(String username, String streetAddress, String zipcode, String city,
			String state) {
		return null;
	}

	@Override
	public boolean deleteShippingAddress(int shippingaddressId, String username) {
		return false;
	}

}
