package com.skilldistillery.planty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
		
	ShippingAddress findByIdAndUser_Username(int shippingAddressId, String username);

}
