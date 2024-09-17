package com.skilldistillery.planty.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Post;
import com.skilldistillery.planty.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> listAllPosts() {
		
		List<Post> allPosts = postRepo.findAll();
		if(allPosts.isEmpty()) {
			throw new EntityNotFoundException("No posts found");
		}
		return allPosts;
	}

}
