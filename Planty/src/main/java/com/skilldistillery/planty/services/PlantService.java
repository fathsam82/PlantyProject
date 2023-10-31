package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.planty.entities.Plant;

public interface PlantService {
	List<Plant> listAllPlants();
	Plant getPlant(int plantId);
	Plant createPlant(Plant newPlant);
	Plant updatePlant(int plantId, Plant newPlant);
	boolean deletePlant(int plantId);

}
