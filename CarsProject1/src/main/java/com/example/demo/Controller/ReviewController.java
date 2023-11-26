package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Bank;
import com.example.demo.Entity.Review;
import com.example.demo.Service.ReviewService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping(value = "/addReview")
	public ResponseEntity<Object> addReview(@RequestBody Review review){
		reviewService.addReview(review);
		return new ResponseEntity<Object>("Review Added SuccessFully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallReviews")
	public ResponseEntity<Object> getallReviews(){
		ResponseEntity<Object> entity = new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
		return entity;
	}
	
	
	
	@GetMapping(value= "/getReview/{feedbackId}")     
	public ResponseEntity<Object> getReviewByFeedBackId(@PathVariable("feedbackId") Long feedbackId )
	{
		Review review;
		
		if(reviewService.isReviewExist(feedbackId)) 
		{
			review = reviewService.getReviewByfeedbackId(feedbackId);
		}
		else {
			review = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(review , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteReview/{feedbackId}")
	public ResponseEntity<Object> deleteReview(@PathVariable("feedbackId") Long feedbackId)
	{
		boolean flag;
		if(reviewService.isReviewExist(feedbackId))
		{
			flag = reviewService.deleteReview(feedbackId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateReview/{feedbackId}")
	public ResponseEntity<Object> updateReview(@PathVariable("feedbackId") Long feedbackId , @RequestBody Review review)
	{
		boolean flag;
		if(reviewService.isReviewExist(feedbackId))
		{
			flag = reviewService.updateReview(review);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	


	@GetMapping(value = "/getReviewsByCustomerId/{customerId}")
	public ResponseEntity<Object> getReviewsByCustomerId(@PathVariable("customerId") Long customerId) {
	    List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
	    
	    if (reviews.isEmpty()) {
	        String message = "Reviews not found based on customerId: " + customerId;
	        ResponseEntity<Object> entity = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	        return entity;
	    } else {
	        ResponseEntity<Object> entity = new ResponseEntity<>(reviews, HttpStatus.OK);
	        return entity;
	    }
	}


}
