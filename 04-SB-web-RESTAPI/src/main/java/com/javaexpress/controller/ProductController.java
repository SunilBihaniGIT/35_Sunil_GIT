package com.javaexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Product;
import com.javaexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public void createProduct(@RequestBody Product product) {
		log.info("ProductController createProduct {} ", product.getName());
		productService.createProduct(product);
	}

	// http://localhost:8080/api/v1/product/1
	@GetMapping("{productId}")
	public Product fetchProduct(@PathVariable long productId) {
		log.info("ProductController fetchProduct {} ");
		return productService.fetchProduct(productId);
	}
	
	// http://localhost:8080/api/v1/product/3
	@PutMapping("{productId}")
	public void updateProduct(@PathVariable long productId , @RequestBody Product product) {
		log.info("ProductController updateProduct {} ");
		productService.updateProduct(productId,product);
	}
	
	@DeleteMapping("{productId}")
	public void deleteProduct(@PathVariable long productId) {
		log.info("ProductController deleteProduct {} ");
		productService.deleteProduct(productId);
	}

	// http://localhost:8080/api/v1/product/category/Mobile
	@GetMapping("/category/{name}")
	public List<Product> fetchProductBasedOnCategoryName(@PathVariable String name) {
		log.info("ProductController fetchProductBasedOnCategoryName {} ");
		return productService.fetchProducts(name);
	}

	// http://localhost:8080/api/v1/product/categoryName/Mobile
	@GetMapping("/categoryName/{name}")
	public List<Product> fetchByCategoryName(@PathVariable String name) {
		log.info("ProductController fetchByCategoryName {} ");
		return productService.findProducts(name);
	}


	// http://localhost:8080/api/v1/product/JPQL/432996c2-8c6a-48ff-9d3b-90d56693121e
	@GetMapping("/JPQL/{barCode}")
	public Product fetchProductUsingJPQL(@PathVariable String barCode) {
		log.info("ProductController fetchProductUsingJPQL {} ");
		return productService.fetchProductUsingJPQL(barCode);
	}	
	
	// http://localhost:8080/api/v1/product/Native/aefe0261-3ec1-4da3-93c3-5696cb3d03db
	@GetMapping("/Native/{barCode}")
	public Product fetchProductUsingNative(@PathVariable String barCode) {
		log.info("ProductController fetchProductUsingNative {} ");
		return productService.fetchProductUsingNative(barCode);
	}   
	
	// http://localhost:8080/api/v1/product/categoryBar/b7abdf8a-97e2-488b-9425-5dcb203739a0
	@GetMapping("/categoryBar/{barCode}")
	public Product fetchProductBasedOnBarCode(@PathVariable String barCode) {
		log.info("ProductController fetchProductBasedOnBarCode {} ");
		return productService.fetchProductUsingBarCode(barCode);
	}

}
