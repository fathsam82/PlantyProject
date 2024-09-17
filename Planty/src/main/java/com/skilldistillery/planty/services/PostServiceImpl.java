package com.skilldistillery.planty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.planty.entities.Post;
import com.skilldistillery.planty.repositories.PostRepository;

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> listAllPosts() {
		
		return null;
	}

}
