package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.entities.Plant;

public interface OrderDetailService {
	
	List<OrderDetail> listAllOrderDetails();
	
	OrderDetail getOrderDetail(int orderDetailId);
	
//	OrderDetail createOrderDetail(OrderDetail newOrderDetail);
	
	OrderDetail updateOrderDetail(int orderDetailId, OrderDetail updatedOrderDetail, String username);

	boolean deleteOrderDetail(int orderDetailId);
	
	OrderDetail createPlantToOrderDetail(int plantId, int quantity, boolean giftWrap, String username);
}
