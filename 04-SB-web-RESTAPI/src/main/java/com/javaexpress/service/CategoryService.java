package com.javaexpress.service;

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

}
