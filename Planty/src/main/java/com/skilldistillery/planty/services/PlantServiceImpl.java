package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

		List<Plant> plants = plantRepo.findAll();
		if (plants.isEmpty()) {
			throw new EntityNotFoundException("Plants not found");
		}
		return plants;
	}

	@Override
	public Optional<Plant> getPlant(int plantId) {
		Optional<Plant> plantOpt = plantRepo.findById(plantId);
		if (!plantOpt.isPresent()) {
			throw new EntityNotFoundException("Plant not found for plant Id: " + plantId);
		}
		return plantOpt;
	}

	@Override
	public Optional<Plant> findByName(String name) {
		Optional<Plant> plantNameOpt = plantRepo.findByNameIgnoreCase(name);
		if(!plantNameOpt.isPresent()) {
			throw new EntityNotFoundException("Plant not found with name: " + name);
		}
		return plantNameOpt;

	}

	@Override
	public List<Plant> findByCat(int plantCatId) {
		List<Plant> plantsOfCategory = plantRepo.findByPlantCatId(plantCatId);
		if(plantsOfCategory.isEmpty()) {
			throw new EntityNotFoundException("Plants of plantCatId " + plantCatId + "not found");
		}
		return plantsOfCategory;
	}

	@Override
	public Plant createPlant(Plant newPlant) {

		return plantRepo.saveAndFlush(newPlant);
	}

	@Override
	public Plant updatePlant(int plantId, Plant newPlant) {
		Plant existingPlant = null;
		Optional<Plant> existingOpt = plantRepo.findById(plantId);
		if (existingOpt.isPresent()) {
			existingPlant = existingOpt.get();
			existingPlant.setDescription(newPlant.getDescription());
			existingPlant.setName(newPlant.getName());
			existingPlant.setPrice(newPlant.getPrice());
			existingPlant.setStockQuantity(newPlant.getStockQuantity());
			existingPlant.setIsDiscounted(newPlant.getIsDiscounted());
			existingPlant.setSize(newPlant.getSize());
			existingPlant.setPlantImageUrl(newPlant.getPlantImageUrl());
			existingPlant.setPlantCat(newPlant.getPlantCat());
			existingPlant.setEnabled(newPlant.getEnabled());
			plantRepo.saveAndFlush(existingPlant);
		}

		return existingPlant;
	}

	@Override
	public boolean deletePlant(int plantId) {
		boolean deleted = false;
		Optional<Plant> toDelete = plantRepo.findById(plantId);
		if (toDelete.isPresent()) {
			plantRepo.delete(toDelete.get());
			deleted = true;
		}

		return deleted;
	}

	@Override
	public boolean disablePlant(int plantId) {
		Plant existingPlant = null;
		Optional<Plant> toDelete = plantRepo.findById(plantId);
		if (toDelete.isPresent()) {
			existingPlant = toDelete.get();
			existingPlant.setEnabled(false);
			plantRepo.save(existingPlant);
			return true;
		}
		return false;
	}

}
