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
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private PlantRepository plantRepo;

	@Autowired
	private OrderCartRepository orderCartRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<OrderDetail> listAllOrderDetails() {

		return orderDetailRepo.findAll();
	}

	@Override
	public OrderDetail getOrderDetail(int orderDetailId) {
		OrderDetail orderDetail = null;
		Optional<OrderDetail> orderDetailOpt = orderDetailRepo.findById(orderDetailId);
		if (orderDetailOpt.isPresent()) {
			orderDetail = orderDetailOpt.get();
		}
		return orderDetail;
	}

//	@Override
//	public OrderDetail createOrderDetail(OrderDetail newOrderDetail) {
//		return orderDetailRepo.saveAndFlush(newOrderDetail);
//	}

	@Override
	public OrderDetail updateOrderDetail(int orderDetailId, OrderDetail updatedOrderDetail, String username) {
		OrderDetail existingOrderDetail = null;
		try {

			User user = userRepo.findByUsername(username);
			if (user == null) {
				throw new Exception("User not found for username: " + username);
			}

			existingOrderDetail = orderDetailRepo.findById(orderDetailId)
					.orElseThrow(() -> new Exception("OrderDetail not found for Id: " + orderDetailId));

			if (!existingOrderDetail.getOrderCart().getUser().equals(user)) {
				throw new Exception("Unauthorized access attempt to update order detail");
			}

			existingOrderDetail.setGiftWrap(updatedOrderDetail.getGiftWrap());
//			existingOrderDetail.setPlant(updatedOrderDetail.getPlant());
			existingOrderDetail.setQuantityOrdered(updatedOrderDetail.getQuantityOrdered());
			Integer subtotalPrice = existingOrderDetail.getPlant().getPrice()* existingOrderDetail.getQuantityOrdered();
			existingOrderDetail.setSubtotalPrice(subtotalPrice);
//			existingOrderDetail.setOrderCart(updatedOrderDetail.getOrderCart());
			

			return orderDetailRepo.saveAndFlush(existingOrderDetail);

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public boolean deleteOrderDetail(int orderDetailId) {
		boolean deleted = false;
		Optional<OrderDetail> toDeleteOpt = orderDetailRepo.findById(orderDetailId);
		if (toDeleteOpt.isPresent()) {
			orderDetailRepo.delete(toDeleteOpt.get());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public OrderDetail createPlantToOrderDetail(int plantId, int quantity, boolean giftWrap, String username) {
		Plant plant = plantRepo.findById(plantId)
				.orElseThrow(() -> new EntityNotFoundException("Plant not found for ID: " + plantId));

		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}
		OrderCart orderCart = user.getOrderCart();
		if (orderCart == null) {
			throw new EntityNotFoundException("OrderCart not found for user: " + username);
		}

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setPlant(plant);
		orderDetail.setQuantityOrdered(quantity);
		orderDetail.setGiftWrap(giftWrap);
		orderDetail.setUnitPrice(plant.getPrice());
		int subtotalPrice = plant.getPrice() * quantity;
		orderDetail.setSubtotalPrice(subtotalPrice);
		orderDetail.setOrderCart(orderCart);

		return orderDetailRepo.saveAndFlush(orderDetail);
	}

}
