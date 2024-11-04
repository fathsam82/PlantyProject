package com.skilldistillery.planty.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.entities.Comment;
import com.skilldistillery.planty.services.CommentService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("comments/{id}")
	public ResponseEntity<?> getCommentsForPost(@PathVariable("id") int postId, Principal principal, HttpServletResponse res){
		if(principal == null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			
			
		}
		try {
			List<Comment> comments = commentService.listAllCommentsForPostId(postId);
			if(comments == null || comments.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No comments found");
			}
			else {
				return ResponseEntity.ok(comments);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while fetching comments :(");
		}
		
		
	}
}










