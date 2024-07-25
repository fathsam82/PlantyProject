package com.skilldistillery.planty.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.OrderCart;
import com.skilldistillery.planty.entities.PaymentData;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.OrderCartRepository;
import com.skilldistillery.planty.repositories.PaymentDataRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class PaymentDataServiceImpl implements PaymentDataService {

	@Autowired
	private PaymentDataRepository paymentDataRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private OrderCartRepository orderCartRepo;

	public List<PaymentData> listPaymentDataForLoggedInUser(String username) {

		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found for username: " + username);
		}
		User user = userOpt.get();

		List<PaymentData> paymentDatas = user.getPaymentDatas();
		if (paymentDatas == null) {
			return new ArrayList<>();
		}
		return paymentDatas;
	}

	@Override
	public PaymentData getPaymentData(int paymentDataId, String username) {
		return paymentDataRepo.findByIdAndUser_Username(paymentDataId, username);
	}

	@Override
	public PaymentData createPaymentData(String username, String cardType, String cardNumber,
			String expirationDateString, String fullName, Boolean enabled) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("User not found");
		}
		
		User user = userOpt.get();

		LocalDate expirationDate;

		try {
			expirationDate = LocalDate.parse(expirationDateString);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid format. Date should be in ISO format (yyyy-MM-dd).");
		}

		if (expirationDate.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Card is expired, genius!");
		}

		PaymentData paymentData = new PaymentData();
		paymentData.setUser(user);
		paymentData.setCardType(cardType);
		paymentData.setCardNumber(cardNumber);
		paymentData.setExpirationDate(expirationDate);
		paymentData.setFullName(fullName);
		paymentData.setEnabled(enabled);

		return paymentDataRepo.saveAndFlush(paymentData);
	}

	@Override
	public boolean deletePaymentData(int paymentDataId, String username) {
		Optional<User> userOpt = userRepo.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new EntityNotFoundException("Username not found for" + username);
		}

		PaymentData paymentData = paymentDataRepo.findByIdAndUser_Username(paymentDataId, username);
		if (paymentData == null) {
			return false;
		}

		OrderCart orderCart = orderCartRepo.findByIdAndUser_Username(paymentDataId, username);
		if (orderCart != null) {
			orderCart.setPaymentData(null);
			orderCartRepo.saveAndFlush(orderCart);
		}

		paymentDataRepo.delete(paymentData);
		return true;
	}

}
