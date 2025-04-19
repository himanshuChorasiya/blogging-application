package com.hblog.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hblog.api.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
}

