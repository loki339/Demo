package com.example.demo.Service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Review;

@Service
public interface ReviewService {

	boolean addReview(Review review);
	List<Review> getAllReviews();
	boolean isReviewExist(Long feedbackId);
	Review getReviewByfeedbackId(Long feedbackId);
	boolean deleteReview(Long feedbackId);
	boolean updateReview(Review review);
	List<Review> getReviewsByCustomerId(Long customerId);
}
