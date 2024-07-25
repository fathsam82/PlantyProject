package com.skilldistillery.planty.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	public OrderCart createOrderCart(String username, OrderCart newOrderCart) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			newOrderCart.setUser(user);
			return orderCartRepo.saveAndFlush(newOrderCart);
		} else {
		throw new EntityNotFoundException("User not found for username: " + username);
	}
	}

	@Override
	public OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
		OrderCart existingCart = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if (existingCart != null) {

			for (OrderDetail updatedDetail : updatedOrderCart.getOrderDetails()) {
				int detailId = updatedDetail.getId();
				orderDetailService.updateOrderDetail(detailId, updatedDetail, username);
			}

			orderCartRepo.saveAndFlush(existingCart);
		}
		return existingCart;
	}

	@Override
	public OrderCart getOrderCartByUsername(String username) throws EntityNotFoundException {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}
		User user = userOpt.get();

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

	@Override
	public boolean removeOrderDetailFromOrderCart(String username, int orderDetailId) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}
		User user = userOpt.get();
		OrderCart orderCart = user.getOrderCart();
		if (orderCart == null) {
			throw new EntityNotFoundException("OrderCart not found for username: " + username);
		}

		boolean removed = orderCart.getOrderDetails().removeIf(detail -> detail.getId() == orderDetailId);
		if (removed) {
			orderDetailRepo.deleteById(orderDetailId);
			orderCartRepo.saveAndFlush(orderCart);
		}
		return removed;

	}

	@Override
	public OrderCart submitOrderCart(String username, int orderCartId) {
		OrderCart cart = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if (cart != null) {
			cart.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(7));
			Random random = new Random();
			cart.setTrackingNumber(1 + random.nextInt(100000000));
			orderCartRepo.save(cart);

			clearOrderDetailsFromOrderCart(cart.getId());
			orderCartRepo.save(cart);

			return cart;
		}
		return null;
	}

	@Override
	public void clearOrderDetailsFromOrderCart(int orderCartId) {
		OrderCart cart = orderCartRepo.findById(orderCartId)
				.orElseThrow(() -> new EntityNotFoundException("OrderCart not found"));
		orderDetailRepo.deleteAll(cart.getOrderDetails());
		cart.getOrderDetails().clear();
		orderCartRepo.save(cart);
	}

}
