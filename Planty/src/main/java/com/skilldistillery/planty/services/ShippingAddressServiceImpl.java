package com.skilldistillery.planty.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.entities.ShippingAddress;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.OrderCartRepository;
import com.skilldistillery.planty.repositories.ShippingAddressRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ShippingAddressRepository shippingAddressRepo;

	@Autowired
	private OrderCartRepository orderCartRepo;

	@Override
	public List<ShippingAddress> listShippingAddressForLoggedInUser(String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username " + username);
		}
		User user = userOpt.get();
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
	public ShippingAddress createShippingAddress(String username, String streetAddress, String city, String state,
			String zipcode, Boolean enabled, String country) {
		Optional<User> userOpt = userRepo.findByUsername(username);

		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username " + username);
		}
		User user = userOpt.get();
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setUser(user);
		shippingAddress.setStreetAddress(streetAddress);
		shippingAddress.setCity(city);
		shippingAddress.setState(state);
		shippingAddress.setZipcode(zipcode);
		shippingAddress.setEnabled(enabled);
		shippingAddress.setCountry(country);

		return shippingAddressRepo.saveAndFlush(shippingAddress);
	}

	@Override
	public boolean deleteShippingAddress(int shippingaddressId, String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}
		ShippingAddress shippingAddress = shippingAddressRepo.findByIdAndUser_Username(shippingaddressId, username);
		if (shippingAddress == null) {
			return false;
		}

		OrderCart orderCart = orderCartRepo.findByIdAndUser_Username(shippingaddressId, username);
		if (orderCart != null) {
			orderCart.setShippingAddress(null);
			orderCartRepo.saveAndFlush(orderCart);
		}
		shippingAddressRepo.delete(shippingAddress);
		return true;
	}

}