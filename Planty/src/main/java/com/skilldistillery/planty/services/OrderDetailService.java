package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.OrderDetail;

public interface OrderDetailService {
	
	List<OrderDetail> listAllOrderDetails();
	
	OrderDetail getOrderDetail(int orderDetailId);
	
	OrderDetail createOrderDetail(int orderDetailId, OrderDetail newOrderDetail);
	
	OrderDetail updateOrderDetail(int orderDetail, OrderDetail updatedOrderDetail);

	boolean deleteOrderDetail(int orderDetailId);
}
