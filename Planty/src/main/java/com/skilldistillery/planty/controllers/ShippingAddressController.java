package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.ShippingAddress;
import com.skilldistillery.planty.services.ShippingAddressService;

@RestController
@CrossOrigin({"*", "http://localhost/" })
@RequestMapping("api")
public class ShippingAddressController {
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@GetMapping("shippingAddress")
	public ResponseEntity<List<ShippingAddress>> listShippingAddressByUsername(
			Principal principal, HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			String username = principal.getName();
			List<ShippingAddress> shippingAddresses = shippingAddressService.listShippingAddressForLoggedInUser(username);
			return ResponseEntity.ok(shippingAddresses);
		} catch (EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("shippingAddress/{shippingAddressId}")
	public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable int shippingAddressId, Principal principal, HttpServletResponse res){
		if(principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			String username = principal.getName();
			ShippingAddress shippingAddress = shippingAddressService.getShippingAddress(shippingAddressId, username);
			return ResponseEntity.ok(shippingAddress);
		} catch (EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

}
