package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{	
	
	//DSL(Domain specific language) - Method 
    // Select * from User where id=1 (findById)
	// Select * from User where username = 'Sunil'
	
	  User findByUsername(String username);	  
	  User findByEmail(String email);
	  User findByUsernameOrEmail(String username,String email);
	  
	// Select * from User where username = 'Sunil' and password ='Admin123'
	  User findByUsernameAndPassword(String username , String password);
	  
	
	
}
