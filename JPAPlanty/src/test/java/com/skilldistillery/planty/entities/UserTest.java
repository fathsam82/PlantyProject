package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {


	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private User user;

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
		
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		user = null;
	}

	@Test
	void test_basic_user_attributes() {
		
		assertNotNull(user);
		
		assertEquals("Samwise", user.getUsername());
		
		assertTrue(user.getEnabled());
		
		assertFalse(!user.getEnabled());
		
		assertEquals("standard", user.getRole());
		
	}
	
	@Test
	void test_user_paymentData_relationship() {
		
		assertNotNull(user);
		
		assertNotNull(user.getPaymentDatas());
		
		assertTrue(user.getPaymentDatas().size()>0);
		
	}
	
	@Test
	void test_user_post_relationship() {
		
		assertNotNull(user);
		
		assertNotNull(user.getPosts());
		
		assertTrue(user.getPosts().size()>0);
		
	}
	
	@Test
	void test_user_comment_relationship() {
		
		assertNotNull(user);
		
		assertNotNull(user.getComments());
		
		assertTrue(user.getComments().size()>0);
		
	}
	
	@Test
	void test_user_orderCart_relationship() {
		
		assertNotNull(user);
		
		assertNotNull(user.getOrderCarts());
		
		assertTrue(user.getOrderCarts().size()>0);
		
	}

}

