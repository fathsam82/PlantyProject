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

//	@Autowired
//	private PlantRepository plantRepo;

	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	public OrderCart createOrderCart(String username, OrderCart newOrderCart) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			newOrderCart.setUser(user);
			return orderCartRepo.saveAndFlush(newOrderCart);
		}
		return null;
	}

	@Override
	public OrderCart checkoutOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
		OrderCart existing = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if (existing != null) {
			existing.setNotes(updatedOrderCart.getNotes());
//			existing.setPaymentMethod(updatedOrderCart.getPaymentMethod());
//			existing.setPaymentData(updatedOrderCart.getPaymentData());   // STILL HAVE TO IMPLEMENT GET / POST / DELETE FOR PAYMENT DATA POJO

			orderCartRepo.saveAndFlush(existing);

		}
		return existing;
	}

	@Override
	public OrderCart updateOrderCart(String username, int orderCartId, OrderCart updatedOrderCart) {
		OrderCart existingCart = orderCartRepo.findByIdAndUser_Username(orderCartId, username);
		if (existingCart != null) {
//			existingCart.setNotes(updatedOrderCart.getNotes());
//			existingCart.setPaymentMethod(updatedOrderCart.getPaymentMethod());
//			existingCart.setPaymentData(updatedOrderCart.getPaymentData());

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

	@Override
	public boolean removeOrderDetailFromOrderCart(String username, int orderDetailId) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			return false;
		}
		OrderCart orderCart = user.getOrderCart();
		if (orderCart == null) {
			return false;
		}

		boolean removed = orderCart.getOrderDetails().removeIf(detail -> detail.getId() == orderDetailId);
		if (removed) {
			orderDetailRepo.deleteById(orderDetailId);
			orderCartRepo.saveAndFlush(orderCart);
		}
		return removed;

	}

	//////////////////////////// SUBMIT ORDER LOGIC

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
