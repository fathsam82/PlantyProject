package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentDataTest {

	
	
private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private PaymentData paymentData;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		emf = Persistence.createEntityManagerFactory("JPAPlanty");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		
		em = emf.createEntityManager();
		
		paymentData = em.find(PaymentData.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		paymentData = null;
	}

	@Test
	void test_basic_payment_data_attributes() {
		
		assertNotNull(paymentData);
		
		assertEquals("1234123412341234", paymentData.getCardNumber());
		
		assertEquals(2024, paymentData.getExpirationDate().getYear());
		
		assertEquals("VISA", paymentData.getCardType());
		
		assertTrue(paymentData.getEnabled());
		
		assertFalse(!paymentData.getEnabled());
		
	}
	
	@Test
	void test_payment_data_user_relationship() {
		
		assertNotNull(paymentData);
		
		assertNotNull("samwise", paymentData.getUser().getUsername());
	}
	
}
