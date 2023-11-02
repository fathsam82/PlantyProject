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

class PlantCategoryTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private PlantCategory plantCat;

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
		
		plantCat = em.find(PlantCategory.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		plantCat = null;
	}

	@Test
	void test_plant_category_basic_attributes() {
		
		assertNotNull(plantCat);
		
		assertEquals("Evergreen perennials", plantCat.getName());
		
		assertEquals("Evergreen perennials have their leaves year-round and live longer than two years.", plantCat.getDescription());
	}

}
