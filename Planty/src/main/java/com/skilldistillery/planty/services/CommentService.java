package com.skilldistillery.planty.services;

import java.util.List;

import com.skilldistillery.planty.entities.Comment;

public interface CommentService {
	
	List<Comment> listAllCommentsForPostId(int postId);

}
