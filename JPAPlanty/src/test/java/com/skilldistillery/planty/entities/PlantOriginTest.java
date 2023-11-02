package com.skilldistillery.planty.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlantOriginTest {
private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private PlantOrigin plantOrigin;

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
		
		plantOrigin = em.find(PlantOrigin.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		em.close();
		
		plantOrigin = null;
	}

	@Test
	void test() {
		
		assertNotNull(plantOrigin);
		
		assertEquals("Ghana", plantOrigin.getName());
		
		BigDecimal expectedLongitude1 = new BigDecimal("0.2057");
	    assertEquals(0, expectedLongitude1.compareTo(plantOrigin.getLongitude()), "Latitude values are not equal");
		
		BigDecimal expectedLatitude1 = new BigDecimal("5.5600");
	    assertEquals(0, expectedLatitude1.compareTo(plantOrigin.getLatitude()), "Latitude values are not equal");
		
		BigDecimal expectedLongitude2 = new BigDecimal("0.2057");
	    assertTrue(expectedLongitude2.compareTo(plantOrigin.getLongitude()) == 0, "Longitude values are not equal");
	    
	    BigDecimal expectedLatitude2 = new BigDecimal("5.5600");
	    assertTrue(expectedLatitude2.compareTo(plantOrigin.getLatitude()) == 0, "Latitude values are not equal");
	}

}



	