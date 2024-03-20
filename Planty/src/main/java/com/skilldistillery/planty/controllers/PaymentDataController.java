package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.PaymentData;
import com.skilldistillery.planty.services.PaymentDataService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class PaymentDataController {

	@Autowired
	private PaymentDataService paymentDataService;

	@GetMapping("paymentData/{username}")
	public ResponseEntity<List<PaymentData>> listPaymentDataByUsername(@PathVariable String username,
			Principal principal, HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			List<PaymentData> paymentDatas = paymentDataService.listPaymentDataByUsername(username);
			return ResponseEntity.ok(paymentDatas);
		} catch (EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("paymentData/{username}/{paymentDataId}")
	public ResponseEntity<PaymentData> getPaymentData(@PathVariable String username, @PathVariable int paymentDataId, Principal principal, HttpServletResponse res) {
	    if (principal == null) {
	        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String authenticatedUsername = principal.getName();
	    if (!authenticatedUsername.equals(username)) {
	        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    try {
	        PaymentData paymentData = paymentDataService.getPaymentData(paymentDataId, username);
	        return ResponseEntity.ok(paymentData);
	    } catch (EntityNotFoundException e) {
	        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
	@PostMapping("paymentData/{username}")
    public ResponseEntity<?> createPaymentData(@PathVariable String username, @RequestBody PaymentData paymentData, Principal principal, HttpServletResponse res) {
        if (principal == null || !principal.getName().equals(username)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            PaymentData newPaymentData = paymentDataService.createPaymentData(
                principal.getName(), 
                paymentData.getCardType(), 
                paymentData.getCardNumber(), 
                paymentData.getExpirationDate().toString()
            );
            return ResponseEntity.ok(newPaymentData);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("paymentData/{username}/{paymentDataId}")
    public ResponseEntity<?> deletePaymentData(@PathVariable String username, @PathVariable int paymentDataId, Principal principal, HttpServletResponse res) {
        if (principal == null || !principal.getName().equals(username)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean isDeleted = paymentDataService.deletePaymentData(paymentDataId, principal.getName());
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
