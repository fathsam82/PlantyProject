package com.skilldistillery.planty.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
	
	Optional<Plant> findByName(String name);

}
