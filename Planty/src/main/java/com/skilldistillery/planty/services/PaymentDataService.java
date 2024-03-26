package com.skilldistillery.planty.services;

import java.util.List;
import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataService {

	List<PaymentData> listPaymentDataForLoggedInUser(String username);

	PaymentData getPaymentData(int paymentDataId, String username);

	PaymentData createPaymentData(String username, String cardType, String cardNumber, String expirationDateString);

	boolean deletePaymentData(int paymentDataId, String username);

}
