package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.OrderCartRepository;
import com.skilldistillery.planty.repositories.OrderDetailRepository;
import com.skilldistillery.planty.repositories.PlantRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class OrderCartServiceImpl implements OrderCartService {

	@Autowired
	private OrderCartRepository orderCartRepo;

	@Autowired
	private UserRepository userRepo;

//	@Autowired
//	private PlantRepository plantRepo;

	@Autowired
	private OrderDetailService orderDetailService;

//	@Override
//	public List<OrderCart> listAllOrderCarts(String username) {
//		
//		return orderCartRepo.findByUser_Username(username);
//	}

//	@Override
//	public OrderCart getOrderCart(String username, int orderCartId) {
//		OrderCart orderCart = null;
//		
//		Optional<OrderCart> orderCartOpt = orderCartRepo.findById(orderCartId);
//		if (orderCartOpt.isPresent() && orderCartOpt.get().getUser().getUsername().equals(username)) {
//			orderCart = orderCartOpt.get();
//		}
//		return orderCart;
//	}

	@Override
	public OrderCart createOrderCart(String username, OrderCart newOrderCart) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			newOrderCart.setUser(user);
			return orderCartRepo.saveAndFlush(newOrderCart);
		}
		return null;
	}

//	@Override
//	@Transactional
//	public OrderCart addPlantToCart(String username, int plantId, int quantity) {
//		User user = userRepo.findByUsername(username);
//		Plant plant = plantRepo.findById(plantId).orElseThrow(null);
//		
//		OrderCart orderCart = user.getOrderCart();
//		
//		if(orderCart == null) {
//			orderCart = new OrderCart();
//			orderCart.setUser(user);
//		}
//		
//		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setPlant(plant);
//		orderDetail.setQuantityOrdered(quantity);
//		orderDetail.setOrderCart(orderCart);
//		orderDetailRepo.saveAndFlush(orderDetail);
//		
//		return orderCart;
//		
//		
//	}

	
	//POTENTIAL CHECKOUT METHOD
//	@Override
//	public OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
//		OrderCart existing = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
//		if(existing != null) {
//			existing.setNotes(updatedOrderCart.getNotes());
//			existing.setPaymentMethod(updatedOrderCart.getPaymentMethod());
//	        orderDetailService.updateOrderDetail(existing.getOrderDetails(), updatedOrderCart.getOrderDetails());
//
//			orderCartRepo.saveAndFlush(existing);
//			
//		}
//		return existing;
//	}

	@Override
	public OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
		OrderCart existingCart = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if (existingCart != null) {
			existingCart.setNotes(updatedOrderCart.getNotes());
			existingCart.setPaymentMethod(updatedOrderCart.getPaymentMethod());
			existingCart.setPaymentData(updatedOrderCart.getPaymentData());

			for (OrderDetail updatedDetail : updatedOrderCart.getOrderDetails()) {
				int detailId = updatedDetail.getId();
				orderDetailService.updateOrderDetail(detailId, updatedDetail, username);
			}

			orderCartRepo.saveAndFlush(existingCart);
		}
		return existingCart;
	}

	@Override
	public boolean deleteOrderCart(String username, int orderCartId) {

		return false;
	}

	@Override
	public OrderCart getOrderCartByUsername(String username) throws EntityNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}

		OrderCart orderCart = user.getOrderCart();
		if (orderCart == null) {
			throw new EntityNotFoundException("OrderCart not found for user: " + username);
		}
		int totalPrice = 0;
		for (OrderDetail detail : orderCart.getOrderDetails()) {
			totalPrice += detail.getSubtotalPrice();

		}

		orderCart.setTotalPrice(totalPrice);

		return orderCart;
	}

}
