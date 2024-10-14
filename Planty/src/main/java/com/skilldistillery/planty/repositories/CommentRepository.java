package com.skilldistillery.planty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.planty.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
