package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataService {
	
	List<PaymentData> listPaymentDataByUsername(String username);
	
	PaymentData getPaymentData(String username, int paymentDataId);
	
	PaymentData createPaymentData(String username, String cardType, String cardNumber, String expirationDateString);
	
	boolean deletePaymentData(String username, int paymentDataId);
	

}
