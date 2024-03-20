package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.PaymentData;
import com.skilldistillery.planty.repositories.PaymentDataRepository;

@Service
public class PaymentDataServiceImpl implements PaymentDataService{
	
	@Autowired
	private PaymentDataRepository paymentDataRepo;

	@Override
	public List<PaymentData> listPaymentDataByUsername(String username) {
		return paymentDataRepo.findByUser_Username(username);
	}

	@Override
	public PaymentData getPaymentData(String username, int paymentDataId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentData createPaymentData(String username, String cardType, String cardNumber,
			String expirationDateString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePaymentData(String username, int paymentDataId) {
		// TODO Auto-generated method stub
		return false;
	}

}
