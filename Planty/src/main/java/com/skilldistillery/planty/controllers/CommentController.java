package com.skilldistillery.planty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.planty.services.CommentService;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	
	
}
