package com.skilldistillery.planty.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Integer> {
	
	OrderCart findByUser_Username(String username);
	
	OrderCart findByIdAndUser_Username(int OrderCartId, String username);

}
