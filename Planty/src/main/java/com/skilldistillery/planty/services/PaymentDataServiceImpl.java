package com.skilldistillery.planty.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.PaymentData;

@Service
public class PaymentDataServiceImpl implements PaymentDataService{

	@Override
	public List<PaymentData> listAllPaymentData(String username) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean deletePaymmentData(String username, int paymentDataId) {
		// TODO Auto-generated method stub
		return false;
	}

}
