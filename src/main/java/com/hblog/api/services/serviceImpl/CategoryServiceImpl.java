package com.hblog.api.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hblog.api.entities.Category;
import com.hblog.api.exceptions.ResourceNotFoundException;
import com.hblog.api.payloads.CategoryDto;
import com.hblog.api.respositories.CategoryRepo;
import com.hblog.api.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category","categoryId", categoryId)));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category","categoryId", categoryId)));
		categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category","categoryId", categoryId)));
		return modelMapper.map(cat, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categoires = categoryRepo.findAll();
		List<CategoryDto> categoryDtoList = categoires.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return categoryDtoList;
	}

}
