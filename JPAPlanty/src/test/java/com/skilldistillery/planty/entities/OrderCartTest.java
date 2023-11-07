package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	
	@Test
	void test_order_cart_order_detail_relationship() {
		
		assertNotNull(orderCart);
		
		assertNotNull(orderCart.getOrderDetails());
		
		assertTrue(orderCart.getOrderDetails().size()>0);
		
//		assertEquals(1000, orderCart.getOrderDetails().getUnitPrice());
//		This does not compile, getOrderDetails is returning a List Object which does not have attributes
	
	}
	
	@Test
	void test_order_cart_payment_data_relationship() {
		
		assertNotNull(orderCart);
		
		assertNotNull(orderCart.getPaymentData());
		
		assertEquals("VISA", orderCart.getPaymentData().getCardType());
		
	}
	
	@Test
	void test_order_cart_user_relationship() {
		
		assertNotNull(orderCart);
		
		assertNotNull(orderCart.getUser());
		
		assertEquals("standard", orderCart.getUser().getRole());
		
	}

}
