package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

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
	public Optional<Plant> getPlant(int plantId) {
		return plantRepo.findById(plantId);
	}

	@Override
	public Optional<Plant> findByName(String name) {
		Optional<Plant> plantOpt = plantRepo.findByNameIgnoreCase(name);
		if (plantOpt.isPresent()) {
			return plantOpt;
		} else {
			return null;
		}
	}

	@Override
	public List<Plant> findByCat(int plantCatId) {
		return plantRepo.findByPlantCatId(plantCatId);
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
			existingPlant.setEnabled(false); // Assuming 'enabled' is a Boolean field that marks active/inactive.
			plantRepo.save(existingPlant);
			return true;
		}
		return false;
	}

}
