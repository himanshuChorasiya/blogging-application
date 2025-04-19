package com.hblog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hblog.api.config.AppConstants;
import com.hblog.api.payloads.ApiResponse;
import com.hblog.api.payloads.PostDto;
import com.hblog.api.payloads.PostResponsePage;
import com.hblog.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	//Create Posts
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer categoryId,@PathVariable Integer userId){
		PostDto post = postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postList = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postList,HttpStatus.OK);
	}
	
	//get post by user
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<List<PostDto>>getPostuser(@PathVariable Integer userId){
			List<PostDto> postList = postService.getPostByUser(userId);
			return new ResponseEntity<List<PostDto>>(postList,HttpStatus.OK);
		}
		
	//get All post
		@GetMapping("/posts")
		public ResponseEntity<PostResponsePage> getAllPosts(
				@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			    @RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageSize,
			    @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			    @RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
			
			PostResponsePage allPosts = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
			return new ResponseEntity<PostResponsePage>(allPosts, HttpStatus.OK);
		}
		
	//get post by Id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId){
			PostDto postDto = postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		}
		
	//Delete posts
		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId){
			postService.deletePost(postId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted succussfully", true),HttpStatus.OK);
			
		}
		
   //update post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
			PostDto updatedPostDto = postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
		}
   
		
	//Search post by keyword
		@GetMapping("/posts/search/{keyword}")
		public ResponseEntity<List<PostDto>>searchPost(@PathVariable String keyword){
			List<PostDto> postDtos = postService.searchPost(keyword);
			return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
		}
		
}











