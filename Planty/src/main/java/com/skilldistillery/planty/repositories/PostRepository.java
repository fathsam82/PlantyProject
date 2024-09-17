package com.skilldistillery.planty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
