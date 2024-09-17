package com.skilldistillery.planty.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.Post;
import com.skilldistillery.planty.services.PostService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@GetMapping("posts")
	public ResponseEntity<?> listPosts(){
		try {
			List<Post> posts = postService.listAllPosts();
			if(posts == null || posts.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No posts found");
			} else {
				return ResponseEntity.ok(posts);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while fetching posts :(");
		}
	}
	

}
