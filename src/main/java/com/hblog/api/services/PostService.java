package com.hblog.api.services;

import java.util.List;

import com.hblog.api.payloads.PostDto;
import com.hblog.api.payloads.PostResponsePage;

public interface PostService {

	//Create Posts
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update posts
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//Delete Posts
	void deletePost(Integer postId);
	
	//Get All Post
	PostResponsePage getAllPost(Integer pageNumber, Integer pageSIze, String sortBy, String sortDir);
	
	//Get single
	PostDto getPostById(Integer postId);
	
	//Get all posts by category
	List<PostDto>getPostByCategory(Integer categoryId);
	
	//Get All post by user
	List<PostDto>getPostByUser(Integer userId);
	
	//Search Posts
	List<PostDto>searchPost(String keyword);
}

