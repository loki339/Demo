package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {
	
    List<Review> findByCustomerId(Long customerId);

}
