package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderCartTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private OrderCart orderCart;

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
		
		orderCart = em.find(OrderCart.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		orderCart = null;
	}

	@Test
	void test_basic_order_cart_attributes() {
		
		assertNotNull(orderCart);
		
		assertEquals(2000, orderCart.getTotalPrice());
		
		assertEquals(1, orderCart.getTrackingNumber());
		
		assertEquals("Credit Card", orderCart.getPaymentMethod());
		
		assertEquals(2023, orderCart.getEstimatedDeliveryDate().getYear());
		
		assertEquals(11, orderCart.getEstimatedDeliveryDate().getMonthValue());
		
		
		
		
		
		
	}

}
