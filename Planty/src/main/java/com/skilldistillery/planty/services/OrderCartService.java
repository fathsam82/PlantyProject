package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.OrderCart;

public interface OrderCartService {

	OrderCart createOrderCart(String username, OrderCart newOrderCart);

	OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart);

	boolean removeOrderDetailFromOrderCart(String username, int orderDetailId);

	OrderCart getOrderCartByUsername(String username);

	OrderCart submitOrderCart(String username, int orderCartId);

	void clearOrderDetailsFromOrderCart(int orderCartId);   //CALLED IN submitOrderCart

//	OrderCart setUserNotesForCheckout(String username, int orderCartId, String notes);

//	OrderCart updatePaymentAndShippingForCheckout(String username, int orderCartIdd, OrderCart updatedOrderCart);


}
