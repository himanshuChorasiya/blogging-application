package com.hblog.api.services;

import java.util.List;

import com.hblog.api.payloads.CategoryDto;

public interface CategoryService {

	//Create category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update Category	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//Delete Category	
	void deleteCategory(Integer categoryId);
	
	//Get Category
	CategoryDto getCategoryById(Integer categoryId);
	
	//Get All category
	List<CategoryDto>getCategories();
	
	
}
