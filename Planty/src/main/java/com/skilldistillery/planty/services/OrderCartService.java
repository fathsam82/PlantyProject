package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.planty.entities.OrderCart;

public interface OrderCartService {

	List<OrderCart> listAllOrderCarts(String username);

	Optional<OrderCart> getOrderCart(String username, int orderCartId);

	OrderCart createOrderCart(String username, OrderCart newOrderCart);

	OrderCart updateOrderCart(String username, int orderCartId);

	boolean deleteOrderCart(String username, int orderCartId);

}
