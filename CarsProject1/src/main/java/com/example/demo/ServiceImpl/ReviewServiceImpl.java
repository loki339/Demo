package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Cars;
import com.example.demo.Entity.Review;
import com.example.demo.Repository.ReviewRepo;
import com.example.demo.Service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepo reviewRepo;
	
	@Override
	public boolean addReview(Review review) {
		reviewRepo.save(review);
		return true;
	}

	@Override
	public List<Review> getAllReviews() {
	 List<Review> reviewList =	reviewRepo.findAll();
	 return reviewList;
	}

	@Override
	public boolean isReviewExist(Long feedbackId) {
		Optional<Review> review = reviewRepo.findById(feedbackId);
		if(review.isPresent())
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Review getReviewByfeedbackId(Long feedbackId) {
		Optional<Review> review1 = reviewRepo.findById(feedbackId);
		if(review1.isPresent()) {
			return review1.get();
		}
		else {
		return null;
	}
	}

	@Override
	public boolean deleteReview(Long feedbackId) {
		reviewRepo.deleteById(feedbackId);
		return true;
	}

	@Override
	public boolean updateReview(Review review) {
		Optional<Review> review2 = reviewRepo.findById(review.getFeedbackId());
		if(review2.isPresent()) {
			reviewRepo.save(review);
			return true;
		}else {
			return false;
		}		
}

	@Override
	public List<Review> getReviewsByCustomerId(Long customerId) {
	    return reviewRepo.findByCustomerId(customerId);
	}

	
	

}
