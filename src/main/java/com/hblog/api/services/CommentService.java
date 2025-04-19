package com.hblog.api.services;

import com.hblog.api.payloads.CommentDto;

public interface CommentService {

	//Create comments
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	//Delete comments
	void deleteComment(Integer commentId);
}
