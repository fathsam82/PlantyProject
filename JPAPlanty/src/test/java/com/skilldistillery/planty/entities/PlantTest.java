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

class PlantTest {
	
	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Plant plant;

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
		
		plant = em.find(Plant.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		plant = null;
	}

	@Test
	void test_basic_plant_attributes() {
		
		assertNotNull(plant);
		
		assertEquals("Snake Plant", plant.getName());
		
		assertFalse(!plant.getIsDiscounted());
		
		assertEquals(1000, plant.getPrice());
		
		assertTrue(plant.getEnabled());
	}
	
	@Test
	void test_plant_plant_origin_relationship() {
		
		assertNotNull(plant);
		
		assertNotNull(plant.getPlantOrigins());
		
		assertTrue(plant.getPlantOrigins().size()>0);	
		
	}
	
	@Test
	void test_plant_plant_category_relationship() {
		
		assertNotNull(plant);
		
		assertNotNull(plant.getPlantCat().getName());
		
		assertEquals("Evergreen perennials", plant.getPlantCat().getName());
	}

}
