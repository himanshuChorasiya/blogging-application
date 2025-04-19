package com.hblog.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hblog.api.payloads.ApiResponse;
import com.hblog.api.payloads.CommentDto;
import com.hblog.api.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
	private CommentService commentService;
	//create comments
    @PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
		CommentDto comments = commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comments, HttpStatus.CREATED);
	}
    
    //delete comments
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse>deleteComments(@PathVariable Integer commentId){
    	commentService.deleteComment(commentId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("Comments deleted successfully",true), HttpStatus.OK);
    }
}
