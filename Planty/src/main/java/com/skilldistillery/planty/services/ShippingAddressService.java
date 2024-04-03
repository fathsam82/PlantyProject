package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.ShippingAddress;

public interface ShippingAddressService {
	
	List<ShippingAddress> listShippingAddressForLoggedInUser(String username);
	
	

}
