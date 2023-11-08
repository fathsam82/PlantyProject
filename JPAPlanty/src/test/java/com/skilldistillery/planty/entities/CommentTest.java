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

class CommentTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Comment comment;

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
		
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		comment = null;
	}

	@Test
	void test_basic_comment_attributes() {
		
		assertNotNull(comment);
		
		assertEquals(2023, comment.getCreatedAt().getYear());
		
		assertEquals(11, comment.getCreatedAt().getMonthValue());
		
		assertEquals("I like creating a space that feels like a forest.", comment.getContent());
		
		assertTrue(comment.getEnabled());
	}
	
	@Test
	void test_comment_user_relationship() {
		
		assertNotNull(comment);
		
		assertNotNull(comment.getUser().getId());
		
		assertNotNull(comment.getUser().getRole());
		
		assertEquals("standard", comment.getUser().getRole());
		
	}
	
	@Test
	void test_comment_post_relationship() {
		
		assertNotNull(comment);
		
		assertNotNull(comment.getPost());
		
		assertEquals(11, comment.getPost().getCreatedAt().getMonthValue());
		
	}

}
