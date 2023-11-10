package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Optional<OrderCart> orderCart = orderCartService.getOrderCart(principal.getName(), orderCartId);
		if(!orderCart.isPresent()) {
			res.setStatus(404);
		}
		return orderCart.get();
	}
	

}
