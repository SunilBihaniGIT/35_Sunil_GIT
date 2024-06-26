package com.javaexpress.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;

import com.javaexpress.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void createCategory(Category category) {
		log.info("CategoryService :: createCategory {}", category.getName());
		categoryRepository.save(category);
		log.info("Category Successfully saved in DB");
	}

	public Category findCategoryById(Long categoryId) {
		log.info("CategoryService :: findCategoryById {}", categoryId);		
		return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
	}

	public void updateCategory(Long categoryId, Category inputCategory) {
		log.info("CategoryService :: updateCategory {}", categoryId);
		Category dbCategory = findCategoryById(categoryId);
		dbCategory.setName(inputCategory.getName());
		categoryRepository.save(dbCategory);
	}

	public void deleteCategory(long categoryId) {
		log.info("CategoryService :: deleteCategory {}", categoryId);
		
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
		} else {
			throw new RuntimeException("Category is not Exist in DB");
		}
	}	

}
