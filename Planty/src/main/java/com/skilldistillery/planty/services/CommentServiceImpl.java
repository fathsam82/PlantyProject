package com.skilldistillery.planty.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Comment;
import com.skilldistillery.planty.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;

	@Override
	public List<Comment> listAllCommentsForPostId(int postId) {
		List<Comment> allCommentsForPostId = commentRepo.findByPostId(postId);
		if(allCommentsForPostId.isEmpty()) {
			throw new EntityNotFoundException("No comments found");
		}
		return allCommentsForPostId;
	}

}
