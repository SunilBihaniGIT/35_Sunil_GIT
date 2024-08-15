package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// DSL - Domain specific Language
	// Input is categoryName and output
	// native query (SQL) we can directly execute in database
	// Jpql- java persistence  Queries - It will support all databases means sql db
	
	// JPQL - It will follow the table name as class name and column name as variable name
	@Query("select p from Product p INNER JOIN p.category c where c.name=:categoryName")
	List<Product> fetchProductByCategoryName(String categoryName);
	
	List<Product> findByCategoryName(String categoryName);
	

	//JPQL Query (p)
	@Query("select p from Product p where p.barCode=:barCode")
	Product fetchProductUsingJPQL(String barCode);
	
	//Native Query (*)
	@Query(value = "select * from Product p where p.bar_code=:barCode" , nativeQuery = true)
	Product fetchProductUsingNative(String barCode);
	
	
	Product findByBarCode(String barCode);
	
}
