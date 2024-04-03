package com.skilldistillery.planty.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Integer> {
		
	PaymentData findByIdAndUser_Username(int paymentDataId, String username);

}
