package com.skilldistillery.planty.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Post;
import com.skilldistillery.planty.entities.User;
import com.skilldistillery.planty.repositories.PostRepository;
import com.skilldistillery.planty.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Post> listAllPosts() {
		
		List<Post> allPosts = postRepo.findAll();
		if(allPosts.isEmpty()) {
			throw new EntityNotFoundException("No posts found");
		}
		return allPosts;
	}

}
