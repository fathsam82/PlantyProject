package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.repositories.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	OrderDetailRepository orderDetailRepo;

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

	@Override
	public OrderDetail createOrderDetail(OrderDetail newOrderDetail) {
		return orderDetailRepo.saveAndFlush(newOrderDetail);
	}

	@Override
	public OrderDetail updateOrderDetail(int orderDetailId, OrderDetail updatedOrderDetail) {
		OrderDetail existing = null;
		Optional<OrderDetail> existingOptional = orderDetailRepo.findById(orderDetailId);
		if(existingOptional.isPresent()) {
			existing = existingOptional.get();
			existing.setGiftWrap(updatedOrderDetail.getGiftWrap());
			existing.setPlant(updatedOrderDetail.getPlant());
			existing.setQuantityOrdered(updatedOrderDetail.getQuantityOrdered());
			int subtotalPrice = existing.getPlant().getPrice() * existing.getQuantityOrdered();
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

	@Override
	public OrderDetail createPlantToOrderDetail(Plant plant, int quantity) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setPlant(plant);
		orderDetail.setQuantityOrdered(quantity);
		
		int subtotalPrice = plant.getPrice() * quantity;
	    orderDetail.setSubtotalPrice(subtotalPrice);
		
		return orderDetailRepo.saveAndFlush(orderDetail);
	}


}
