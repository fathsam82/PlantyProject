package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderDetail;
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
	public OrderDetail createOrderDetail(int orderDetailId, OrderDetail newOrderDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail updateOrderDetail(int orderDetail, OrderDetail updatedOrderDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteOrderDetail(int orderDetailId) {
		// TODO Auto-generated method stub
		return false;
	}

}
