package com.skilldistillery.planty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.PaymentData;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Integer> {

}
