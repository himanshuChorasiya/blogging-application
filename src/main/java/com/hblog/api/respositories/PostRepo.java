package com.hblog.api.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hblog.api.entities.Category;
import com.hblog.api.entities.Post;
import com.hblog.api.entities.User;
public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
