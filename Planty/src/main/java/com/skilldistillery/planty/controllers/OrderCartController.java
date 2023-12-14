package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.services.OrderCartService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class OrderCartController {
	
	@Autowired
	private OrderCartService orderCartService;
	
//	@GetMapping("orderCarts")
//	public List<OrderCart> listAllOrderCarts(Principal principal, HttpServletResponse res, HttpServletRequest req){
//		
//		return orderCartService.listAllOrderCarts(principal.getName());
//	}
	
//	@GetMapping("orderCarts/{orderCartId}")
//	public OrderCart getOrderCart(Principal principal, @PathVariable Integer orderCartId, HttpServletResponse res, HttpServletRequest req) {
//		OrderCart orderCart = orderCartService.getOrderCart(principal.getName(), orderCartId);
//		if(orderCart == null) {
//			res.setStatus(404);
//		}
//		return orderCart;
//	}
	
	@GetMapping("orderCarts")
	public ResponseEntity<OrderCart> getOrderCart(Principal principal, HttpServletResponse res) {
	    if (principal == null) {
	        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    try {
	        String username = principal.getName();
	        OrderCart orderCart = orderCartService.getOrderCartByUsername(username);
	        return ResponseEntity.ok(orderCart);
	    } catch (EntityNotFoundException e) {
	        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
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
	
	@PutMapping("orderCarts/{orderCartId}")
	public OrderCart updateOrderCart(Principal principal, HttpServletResponse res, @PathVariable("orderCartId")int OrderCartId, @RequestBody OrderCart orderCart) {
		orderCart = orderCartService.updateOrderCart(principal.getName(), OrderCartId, orderCart);
		try {
			if (orderCart == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			orderCart = null;
		}
		return orderCart;

	}
	
	
//	@PostMapping("orderCarts/addPlant")
//	public OrderCart addPlantToCart(Principal principal, 
//	                                @RequestParam("plantId") int plantId, 
//	                                @RequestParam("quantity") int quantity, 
//	                                HttpServletResponse res, 
//	                                HttpServletRequest req) {
//	    try {
//	        OrderCart updatedCart = orderCartService.addPlantToCart(principal.getName(), plantId, quantity);
//	        if (updatedCart != null) {
//	            res.setStatus(HttpServletResponse.SC_OK); // 200 OK
//	            return updatedCart;
//	        } else {
//	            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
//	            return null;
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
//	        return null;
//	    }
//	}
	

}
