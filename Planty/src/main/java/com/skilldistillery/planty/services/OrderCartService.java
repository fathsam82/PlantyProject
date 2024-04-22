package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.OrderCart;

public interface OrderCartService {

//	List<OrderCart> listAllOrderCarts(String username);

//	OrderCart getOrderCart(String username, int orderCartId);

	OrderCart createOrderCart(String username, OrderCart newOrderCart);

//	OrderCart addPlantToCart(String username, int plantId, int quantity);

	OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart);


	boolean deleteOrderCart(String username, int orderCartId);

	boolean removeOrderDetailFromOrderCart(String username, int orderDetailId);

	OrderCart getOrderCartByUsername(String username);

	OrderCart submitOrderCart(String username, int orderCartId);

	void clearOrderDetailsFromOrderCart(int orderCartId);

	OrderCart setUserNotes(String username, int orderCartId, String notes);

	OrderCart updatePaymentAndShipping(String username, int orderCartIdd, OrderCart updatedOrderCart);


}
