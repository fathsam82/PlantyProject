package com.skilldistillery.planty.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<?> listPlants() {
		try {
			List<Plant> plants = plantService.listAllPlants();

			if (plants == null || plants.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No plants found");
			} else {
				return ResponseEntity.ok(plants);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while fetching plants :(");
		}

	}

	@GetMapping("plants/{id}")
	public ResponseEntity<?> getPlantById(@PathVariable("id") int plantId) {
		try {
			Optional<Plant> plant = plantService.getPlant(plantId);
			if (plant.isPresent()) {
				return ResponseEntity.ok(plant.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Plant not found with id: " + plantId + " \uD83E\uDD14");
			}
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching the plant \u2639");
		}

	}

	@GetMapping("plants/name/{name}")
	public ResponseEntity<?> getPlantByName(@PathVariable("name") String name) {
		try {
			Optional<Plant> plant = plantService.findByName(name);
			if (plant.isPresent()) {
				return ResponseEntity.ok(plant.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Plant not found with name: " + name + " \uD83E\uDD14");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching the plant \u2639");

		}

	}

	@GetMapping("plants/plantCatId/{plantCatId}")
	public ResponseEntity<?> getPlantsByCat(@PathVariable("plantCatId") int plantCatId) {
		try {
			List<Plant> plants = plantService.findByCat(plantCatId);

			if (plants.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
			} else {
				return ResponseEntity.ok(plants);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching plants :(");
		}

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
	public Plant updatePlant(@PathVariable("id") int plantId, @RequestBody Plant updatedPlant,
			HttpServletResponse res) {
		updatedPlant = plantService.updatePlant(plantId, updatedPlant);

		if (updatedPlant == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return updatedPlant;
	}

	@DeleteMapping("plants/{plantId}")
	public void deletePlant(@PathVariable("plantId") int plantId, HttpServletResponse res) {
		if (plantService.deletePlant(plantId)) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}

	}

	@DeleteMapping("plants/toDisable/{plantId}")
	public void disablePlant(@PathVariable("plantId") int plantId, HttpServletResponse res) {
		if (plantService.disablePlant(plantId)) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}

	}

}
