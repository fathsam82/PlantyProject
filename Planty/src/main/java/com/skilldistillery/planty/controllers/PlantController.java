package com.skilldistillery.planty.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.Plant;
import com.skilldistillery.planty.services.PlantService;

@RestController
@RequestMapping("api")
public class PlantController {
	
	@Autowired
	private PlantService plantService;
	
	@GetMapping("plants")
	List<Plant> listPlants() {
		
		return plantService.listAllPlants();
		
	}
	
	
	

}
