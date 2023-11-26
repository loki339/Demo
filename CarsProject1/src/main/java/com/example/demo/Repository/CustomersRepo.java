package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Customers;

public interface CustomersRepo extends JpaRepository<Customers, Long> {
	  @Query("SELECT c FROM Customers c WHERE c.emailId = :email")
	    Optional<Customers> findByEmail(@Param("email") String email);
	  
	  
	  @Query("SELECT c FROM Customers c WHERE c.emailId = :email AND c.password = :password")
	  Optional<Customers> findByEmailAndPass(@Param("email") String email, @Param("password") String password);

}
