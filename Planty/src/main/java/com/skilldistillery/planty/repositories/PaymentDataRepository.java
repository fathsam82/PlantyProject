package com.skilldistillery.planty.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Integer> {
	
	List<PaymentData> findByUser_Username(String username);
	
	PaymentData findByIdAndUser_Username(int paymentDataId, String username);

}
