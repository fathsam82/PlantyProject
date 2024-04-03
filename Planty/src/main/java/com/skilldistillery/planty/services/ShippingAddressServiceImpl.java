package com.skilldistillery.planty.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.ShippingAddress;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.ShippingAddressRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
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
		
		return shippingAddressRepo.findByIdAndUser_Username(shippingAddressId, username);
	}

	@Override
	public ShippingAddress createShippingAddress(String username, String streetAddress, String zipcode, String city,
			String state, String country, Boolean enabled) {
		User user = userRepo.findByUsername(username);
		
		if (user == null) {
			throw new EntityNotFoundException("User not found for username " + username);
		}
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setUser(user);
		shippingAddress.setStreetAddress(streetAddress);
		shippingAddress.setCity(city);
		shippingAddress.setState(state);
		shippingAddress.setZipcode(zipcode);
		shippingAddress.setCountry(country);
		shippingAddress.setEnabled(enabled);
		
		return shippingAddressRepo.saveAndFlush(shippingAddress);
	}

	@Override
	public boolean deleteShippingAddress(int shippingaddressId, String username) {
		ShippingAddress shippingAddress = shippingAddressRepo.findByIdAndUser_Username(shippingaddressId, username);
		if(shippingAddress == null) {
			return false;
		}
		shippingAddressRepo.delete(shippingAddress);
		return true;
}

}