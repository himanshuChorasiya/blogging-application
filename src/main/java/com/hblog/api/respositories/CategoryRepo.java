package com.hblog.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hblog.api.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
