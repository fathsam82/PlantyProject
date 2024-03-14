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
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PutMapping("orderCarts/checkout/{orderCartId}")
	public OrderCart checkoutOrderCart(Principal principal, HttpServletResponse res, @PathVariable("orderCartId")int OrderCartId, @RequestBody OrderCart orderCart) {
		orderCart = orderCartService.checkoutOrderCart(principal.getName(), OrderCartId, orderCart);
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
	
	@DeleteMapping("orderCarts/{orderCartId}/details/{orderDetailId}")
	public ResponseEntity<?> removeOrderDetailFromOrderCart(Principal principal, @PathVariable("orderCartId") int orderCartId, @PathVariable("orderDetailId") int orderDetailId, HttpServletResponse res) {
	    if (principal == null) {
	        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    try {
	        String username = principal.getName();
	        boolean success = orderCartService.removeOrderDetailFromOrderCart(username, orderDetailId);
	        
	        if (success) {
	            return ResponseEntity.ok().build();
	        } else {
	            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("orderDetail with ID " + orderDetailId + " not found with orderCart ID " + orderCartId);
	        }
	    } catch (Exception e) {
	        res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing the order detail");
	    }
	}

	
	
}
