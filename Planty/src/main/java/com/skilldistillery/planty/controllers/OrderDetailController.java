package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.OrderDetail;
import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.services.OrderDetailService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("orderDetails")
	List<OrderDetail> listOrderDetails(HttpServletResponse res) {
		List<OrderDetail> orderDetails = null;
		orderDetails = orderDetailService.listAllOrderDetails();
		if (orderDetails == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return orderDetails;
	}

	@GetMapping("orderDetails/{id}")
	OrderDetail getOrderDetailById(@PathVariable("id") int orderDetailId, HttpServletResponse res) {
		OrderDetail orderDetailOpt = orderDetailService.getOrderDetail(orderDetailId);
		if (orderDetailOpt != null) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return orderDetailOpt;

	}

	@PutMapping("orderDetails/{orderDetailId}")
	OrderDetail updateOrderDetail(@RequestBody OrderDetail updatedOrderDetail, @PathVariable int orderDetailId,
			Principal principal, HttpServletResponse res) {

		String username = principal.getName();
		updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetailId, updatedOrderDetail, username);
		try {
			if (updatedOrderDetail == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			updatedOrderDetail = null;
		}
		return updatedOrderDetail;
	}

	@DeleteMapping("orderDetails/{orderDetailId}")
	public void deleteOrderDetail(@PathVariable int orderDetailId, HttpServletResponse res) {
		try {
			if (orderDetailService.deleteOrderDetail(orderDetailId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

	@PostMapping("orderDetails/addPlant/{plantId}/{quantity}/{giftWrap}")
	public ResponseEntity<?> addPlantToOrderDetail(@PathVariable int plantId, @PathVariable int quantity,
			@PathVariable boolean giftWrap, Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("User was not able to be authorized for this request");
		}
		OrderDetail orderDetail;
		try {
			String username = principal.getName();
			orderDetail = orderDetailService.createPlantToOrderDetail(plantId, quantity, giftWrap, username);
			if (orderDetail != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderDetail was not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured when trying to create an OrderDetail");

		}
	}
}
