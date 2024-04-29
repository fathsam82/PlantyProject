package com.skilldistillery.planty.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.entities.PlantOrigin;
import com.skilldistillery.planty.repositories.PlantOriginRepository;
import com.skilldistillery.planty.repositories.PlantRepository;

@Service
public class PlantOriginServiceImpl implements PlantOriginService {
	
	@Autowired
	private PlantOriginRepository plantOriginRepo;
	
	@Autowired PlantRepository plantRepo;
	
	
	
	
	
	public PlantOrigin getPlantOriginByPlantId(int plantId) {
        Optional<Plant> plantOpt = plantRepo.findById(plantId);
        if (plantOpt.isPresent()) {
            Plant plant = plantOpt.get();
            return plant.getPlantOrigin();
        } else {
            throw new EntityNotFoundException("No plant found with ID: " + plantId);
        }
    }

}
