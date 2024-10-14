package com.skilldistillery.planty.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.planty.entities.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	@Override
	public List<Comment> listAllCommentsForPostId(int postId) {
		return null;
	}

}
