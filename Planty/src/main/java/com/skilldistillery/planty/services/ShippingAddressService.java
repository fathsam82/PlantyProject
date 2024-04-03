package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.ShippingAddress;

public interface ShippingAddressService {

	List<ShippingAddress> listShippingAddressForLoggedInUser(String username);

	ShippingAddress getShippingAddress(int shippingAddressId, String username);

	ShippingAddress createShippingAddress(String username, String streetAddress, String zipcode, String city,
			String state);

	boolean deleteShippingAddress(int shippingaddressId, String username);

}
