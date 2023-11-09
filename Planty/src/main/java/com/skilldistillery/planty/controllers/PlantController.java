package com.skilldistillery.planty.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.services.PlantService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class PlantController {

	@Autowired
	private PlantService plantService;

	@GetMapping("plants")
	List<Plant> listPlants(HttpServletResponse res) {
		List<Plant> plants = null;
		plants = plantService.listAllPlants();

		if (plants == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}

		return plants;

	}

	@GetMapping("plants/{id}")
	Plant getPlantById(@PathVariable("id") int plantId, HttpServletResponse res) {
		Optional<Plant> plant = plantService.getPlant(plantId);
		if (plant.isPresent()) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}

		return plant.get();

	}

	@GetMapping("plants/name/{name}")
	Plant getPlantByName(@PathVariable("name") String name, HttpServletResponse res) {
		Optional<Plant> plant = plantService.findByName(name);
		if (plant.isPresent()) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return plant.get();
	}

	@GetMapping("plants/plantCatId/{plantCatId}")
	List<Plant> getPlantsByCat(@PathVariable("plantCatId") int plantCatId, HttpServletResponse res) {
		List<Plant> plants = null;
		plants = plantService.findByCat(plantCatId);

		if (plants == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return plants;
	}

	@PostMapping("plants")
	public Plant createPlant(@RequestBody Plant plant, HttpServletResponse res, HttpServletRequest req) {
		Plant newPlant = plantService.createPlant(plant);
		if (newPlant != null) {
			res.setStatus(201);
			StringBuffer url = req.getRequestURL().append("/" + newPlant.getId());
			res.setHeader("Location", url.toString());
		} else {
			res.setStatus(400);
		}
		return newPlant;

	}
	
	@PutMapping("plants/{id}")
	public Plant updatePlant(@PathVariable("id") int plantId, @RequestBody Plant updatedPlant, HttpServletResponse res) {
		updatedPlant = plantService.updatePlant(plantId, updatedPlant);
		
		if(updatedPlant == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return updatedPlant;
	}

}
