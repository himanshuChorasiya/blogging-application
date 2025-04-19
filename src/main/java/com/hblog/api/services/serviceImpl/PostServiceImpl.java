package com.hblog.api.services.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hblog.api.entities.Category;
import com.hblog.api.entities.Post;
import com.hblog.api.entities.User;
import com.hblog.api.exceptions.ResourceNotFoundException;
import com.hblog.api.payloads.PostDto;
import com.hblog.api.payloads.PostResponsePage;
import com.hblog.api.respositories.CategoryRepo;
import com.hblog.api.respositories.PostRepo;
import com.hblog.api.respositories.UserRepo;
import com.hblog.api.services.PostService;

import net.bytebuddy.asm.Advice.This;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer categoryId, Integer userId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category","categoryId", categoryId)));
		
		User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id", userId)));
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost = postRepo.save(post);
		
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setAddedDate(postDto.getAddDate());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPostDto = postRepo.save(post);
		return modelMapper.map(updatedPostDto, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		postRepo.delete(post);

	}

	@Override
	public PostResponsePage getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		//Implementing pagination
	//	Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
	
		//for paging & sorting
	//Sort ascending or descending
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			sort = Sort.by(sortBy).ascending();
		else 
			sort = Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepo.findAll(pageable);
		
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDtoList = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponsePage postResponsePage = new PostResponsePage();
		postResponsePage.setContent(postDtoList);
		postResponsePage.setPageNumber(pagePost.getNumber());
		postResponsePage.setPageSize(pagePost.getSize());
		postResponsePage.setTotalPages(pagePost.getTotalPages());
		postResponsePage.setLastPage(pagePost.isLast());
		return postResponsePage;
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category","categoryId", categoryId)));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtoList = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
	
		User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","UseId", userId)));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtoList = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
	
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtoList = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

}
