package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.planty.entities.Plant;

public interface PlantService {
	
	List<Plant> listAllPlants();

	Optional<Plant> getPlant(int plantId);
	
	Optional<Plant> findByName(String name);
	
	List<Plant> findByCat(int plantCatId);

	Plant createPlant(Plant newPlant);

	Plant updatePlant(int plantId, Plant newPlant);

	boolean deletePlant(int plantId);
	
	boolean disablePlant(int plantId);

}
