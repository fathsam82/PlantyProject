package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

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

	@GetMapping("paymentData")
	public ResponseEntity<List<PaymentData>> listPaymentDataByUsername(Principal principal, HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			String username = principal.getName();
			List<PaymentData> paymentDatas = paymentDataService.listPaymentDataForLoggedInUser(username);
			return ResponseEntity.ok(paymentDatas);
		} catch (EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("paymentData/{paymentDataId}")
	public ResponseEntity<PaymentData> getPaymentData(@PathVariable int paymentDataId, Principal principal,
			HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			String username = principal.getName();
			PaymentData paymentData = paymentDataService.getPaymentData(paymentDataId, username);
			if (paymentData == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(paymentData);
		} catch (EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("paymentData")
	public ResponseEntity<?> createPaymentData(@RequestBody PaymentData paymentData, Principal principal,
			HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			String username = principal.getName();
			PaymentData newPaymentData = paymentDataService.createPaymentData(username, paymentData.getCardType(),
					paymentData.getCardNumber(), paymentData.getExpirationDate().toString(), paymentData.getFullName(),
					paymentData.getEnabled());
			return ResponseEntity.ok(newPaymentData);
		} catch (IllegalArgumentException | EntityNotFoundException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("paymentData/{paymentDataId}")
	public ResponseEntity<?> deletePaymentData(Principal principal, @PathVariable("paymentDataId") int paymentDataId,
			HttpServletResponse res) {
		if (principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			String username = principal.getName();
			boolean success = paymentDataService.deletePaymentData(paymentDataId, username);

			if (success) {
				return ResponseEntity.ok().build();
			} else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("orderDetail with ID " + paymentDataId + " not found with orderCart ID " + paymentDataId);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting paymentData");
		}

	}
}
