package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDetailTest {

	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private OrderDetail orderDetail;

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
		
		orderDetail = em.find(OrderDetail.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		orderDetail = null;
	}

	@Test
	void test_basic_order_detail_attributes() {
		
		assertNotNull(orderDetail);
		
		assertEquals(2, orderDetail.getQuantityOrdered());
		
		assertEquals(1000, orderDetail.getUnitPrice());
		
		assertFalse(orderDetail.getGiftWrap());
	}

}
