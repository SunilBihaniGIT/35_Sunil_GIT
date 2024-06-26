package com.javaexpress.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaexpress.entities.Product;
import com.javaexpress.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public void createProduct(@RequestBody Product product) {
		log.info("ProductService  createProduct {}",product.getName());
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
	}

	public Product fetchProduct(@PathVariable long productId) {
		log.info("ProductService  fetchProduct {}");
		return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product is not available"));
	}

	public List<Product> fetchProducts(@PathVariable String categoryName) {
		log.info("ProductService  fetchProducts {}");
		return productRepository.fetchProductByCategoryName(categoryName);
	}
	
	public List<Product> findProducts(@PathVariable String categoryName) {
		log.info("ProductService  findProducts {}");
		return productRepository.findByCategoryName(categoryName);
	}


	public Product fetchProductUsingJPQL(@PathVariable String barCode) {
		log.info("ProductService  fetchProductUsingJPQL {}");
		return productRepository.fetchProductUsingJPQL(barCode);
	}
	
	public Product fetchProductUsingNative(@PathVariable String barCode) {
		log.info("ProductService  fetchProductUsingNative {}");
		return productRepository.fetchProductUsingNative(barCode);
	}
	
	
	public Product fetchProductUsingBarCode(@PathVariable String barCode) {
		log.info("ProductService  fetchProductUsingBarCode {}");
		return productRepository.findByBarCode(barCode);
	}

	public void updateProduct(long productId, Product product) {
		log.info("ProductService  updateProduct {}");
		Product dbProduct = fetchProduct(productId);		
		
		dbProduct.setCategory(product.getCategory());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setName(product.getName());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setQuantity(product.getQuantity());
		
		productRepository.save(dbProduct);		
	}

	public void deleteProduct(long productId) {
		log.info("ProductService  deleteProduct {}");
		
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
		} else {
			throw new RuntimeException("Product is not Exist in DB");
		}
	}	

}
