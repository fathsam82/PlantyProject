package com.skilldistillery.planty.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.PlantOrigin;
import com.skilldistillery.planty.services.PlantOriginService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class PlantOriginController {

	@Autowired
	private PlantOriginService plantOriginService;

	@GetMapping("plantOrigin/{id}")
	public ResponseEntity<?> getPlantOriginByPlantId(@PathVariable("id") int plantId) {
		try {
			PlantOrigin plantOrigin = plantOriginService.getPlantOriginByPlantId(plantId);
			if (plantOrigin != null) {
				return ResponseEntity.ok(plantOrigin);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Origin not found for plant with id: " + plantId + " 🌍");
			}
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Crikey! An error occurred while fetching the plant's origin 😖");
		}
	}

}
