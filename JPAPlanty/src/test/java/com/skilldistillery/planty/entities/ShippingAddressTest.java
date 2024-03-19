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

class ShippingAddressTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ShippingAddress shipAddress;

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

		shipAddress = em.find(ShippingAddress.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {

		em.close();

		shipAddress = null;
	}
	
	@Test
	void test_basic_shipping_address_attributes() {
		
		assertNotNull(shipAddress);
		
		assertEquals("4567 sawmill blvd", shipAddress.getStreetAddress());
	}
}
