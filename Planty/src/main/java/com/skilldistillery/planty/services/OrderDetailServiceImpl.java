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
		if(orderDetailOpt.isPresent()) {
			orderDetail = orderDetailOpt.get();
		}
		return orderDetail;
	}

//	@Override
//	public OrderDetail createOrderDetail(OrderDetail newOrderDetail) {
//		return orderDetailRepo.saveAndFlush(newOrderDetail);
//	}

	@Override
	public OrderDetail updateOrderDetail(int orderDetailId, OrderDetail updatedOrderDetail) {
		OrderDetail existing = null;
		Optional<OrderDetail> existingOptional = orderDetailRepo.findById(orderDetailId);
		if(existingOptional.isPresent()) {
			existing = existingOptional.get();
			existing.setGiftWrap(updatedOrderDetail.getGiftWrap());
			existing.setPlant(updatedOrderDetail.getPlant());
			existing.setQuantityOrdered(updatedOrderDetail.getQuantityOrdered());
			Integer subtotalPrice = existing.getPlant().getPrice() * existing.getQuantityOrdered();
	        existing.setSubtotalPrice(subtotalPrice);
//			existing.setOrderCart(updatedOrderDetail.getOrderCart());
			orderDetailRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean deleteOrderDetail(int orderDetailId) {
		boolean deleted = false;
		Optional<OrderDetail> toDeleteOpt = orderDetailRepo.findById(orderDetailId);
		if(toDeleteOpt.isPresent()) {
			orderDetailRepo.delete(toDeleteOpt.get());
			deleted = true;
		}
		return deleted;
	}

//	@Override
//	public OrderDetail createPlantToOrderDetail(int plantId, int quantity, int orderCartId) {
//		
//		Plant plant = plantRepo.findById(plantId).orElseThrow(() -> new EntityNotFoundException("Plant not found for ID: " + plantId));
//		
//		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setPlant(plant);
//		orderDetail.setQuantityOrdered(quantity);
//		int subtotalPrice = plant.getPrice() * quantity;
//	    orderDetail.setSubtotalPrice(subtotalPrice);
//	    OrderCart orderCart = orderCartRepo.findById(orderCartId).orElseThrow(() -> new EntityNotFoundException("OrderCart not found for ID: " + orderCartId));
//	    orderDetail.setOrderCart(orderCart);
//		return orderDetailRepo.saveAndFlush(orderDetail);
//	}

	
	@Override
	public OrderDetail createPlantToOrderDetail(int plantId, int quantity, String username) {
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
	    int subtotalPrice = plant.getPrice() * quantity;
	    orderDetail.setSubtotalPrice(subtotalPrice);
	    orderDetail.setOrderCart(orderCart);

	    return orderDetailRepo.saveAndFlush(orderDetail);
	}

}
