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

class PostTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Post post;

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

		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {

		em.close();

		post = null;
	}

	@Test
	void test_basic_post_attributes() {

		assertNotNull(post);

		assertEquals("What is one benefit you've received after incorporating house plants into your decor?",post.getTitle());
		
		assertEquals(2023, post.getCreatedAt().getYear());
		
		assertEquals(11, post.getCreatedAt().getMonthValue());
		
		assertEquals(13, post.getCreatedAt().getHour());
	}
	
	@Test
	void test_post_user_relationship() {
		
		assertNotNull(post);
		
		assertNotNull(post.getUser().getUsername());
		
		assertEquals("samwise", post.getUser().getUsername());
		
	}
	
	@Test
	void test_post_comment_relationship() {
		
		assertNotNull(post);
		
		assertNotNull(post.getComments());
		
		assertTrue(post.getComments().size()>0);
	}

}
