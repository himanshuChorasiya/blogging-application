package com.hblog.api.services.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hblog.api.entities.Comment;
import com.hblog.api.entities.Post;
import com.hblog.api.exceptions.ResourceNotFoundException;
import com.hblog.api.payloads.CommentDto;
import com.hblog.api.respositories.CommentRepo;
import com.hblog.api.respositories.PostRepo;
import com.hblog.api.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment  = commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
        commentRepo.delete(comment);
	}

}
