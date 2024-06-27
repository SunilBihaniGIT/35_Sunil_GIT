package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Category;
import com.javaexpress.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createCategory(@RequestBody Category category) {
		log.info("CategoryController :: createCategory {}", category.getName());
		categoryService.createCategory(category);
	}
}
