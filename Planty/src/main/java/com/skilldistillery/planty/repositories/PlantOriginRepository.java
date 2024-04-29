package com.skilldistillery.planty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.PlantOrigin;

public interface PlantOriginRepository extends JpaRepository<PlantOrigin, Integer> {
	
	

}
