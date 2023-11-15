package com.skilldistillery.planty.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.services.OrderDetailService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class OrderDetailController {
	
	@Autowired 
	OrderDetailService orderDetailService;
	
	@GetMapping("orderDetails")
	List<OrderDetail> listOrderDetails(HttpServletResponse res){
		List<OrderDetail> orderDetails = null;
		
		orderDetails = orderDetailService.listAllOrderDetails();
		
		if(orderDetails == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return orderDetails;
	}

}
