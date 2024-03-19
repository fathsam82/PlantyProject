package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataService {
	
	List<PaymentData> listAllPaymentData();
	
	PaymentData getPaymentData(String username, int paymentDataId);
	
	PaymentData createPaymentData(String username, String streetAddress, String zipcode, String city, String state);
	
	boolean deletePaymmentData(String username, int paymentDataId);
	

}
