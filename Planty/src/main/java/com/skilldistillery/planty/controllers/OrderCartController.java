package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.services.OrderCartService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class OrderCartController {
	
	@Autowired
	private OrderCartService orderCartService;
	
	@GetMapping("orderCarts")
	public List<OrderCart> listAllOrderCarts(Principal principal, HttpServletResponse res, HttpServletRequest req){
		
		return orderCartService.listAllOrderCarts(principal.getName());
	}
	
	@GetMapping("orderCarts/{orderCartId}")
	public OrderCart getOrderCart(Principal principal, @PathVariable("orderCartId") int orderCartId, HttpServletResponse res, HttpServletRequest req) {
		OrderCart orderCart = orderCartService.getOrderCart(principal.getName(), orderCartId);
		if(orderCart == null) {
			res.setStatus(404);
		}
		return orderCart;
	}
	
	@PostMapping("orderCarts")
	public OrderCart createOrderCart(Principal principal, @RequestBody OrderCart newOrderCart, HttpServletResponse res, HttpServletRequest req) {
		newOrderCart = orderCartService.createOrderCart(principal.getName(), newOrderCart);
		if(newOrderCart == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.setHeader("Location", url.append("/").append(newOrderCart.getId()).toString());
		}
		return newOrderCart;
	}
	

}
