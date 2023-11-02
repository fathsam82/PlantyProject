package com.skilldistillery.planty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.repositories.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
	
	@Autowired
	private PlantRepository plantRepo;

	@Override
	public List<Plant> listAllPlants() {
		
		return plantRepo.findAll();
		
		
	}

	@Override
	public Plant getPlant(int plantId) {
	
		return null;
	}

	@Override
	public Plant createPlant(Plant newPlant) {
		
		return null;
	}

	@Override
	public Plant updatePlant(int plantId, Plant newPlant) {
		
		return null;
	}

	@Override
	public boolean deletePlant(int plantId) {
		
		return false;
	}

}
