package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedbackid", nullable = false)
    private Long feedbackId;

    @Column(name = "feedbackdate")
    private String feedbackDate;

    @Column(name = "review", length = 500)
    private String review;

    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "customerid", nullable = false)
    private Long customerId;
    
    @Column(name = "carId", nullable = false)
    private Long carId;


	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Review(Long feedbackId, String feedbackDate, String review, Integer rating, Long customerId, Long carId) {
		super();
		this.feedbackId = feedbackId;
		this.feedbackDate = feedbackDate;
		this.review = review;
		this.rating = rating;
		this.customerId = customerId;
		this.carId = carId;
	}


	@Override
	public String toString() {
		return "Review [feedbackId=" + feedbackId + ", feedbackDate=" + feedbackDate + ", review=" + review
				+ ", rating=" + rating + ", customerId=" + customerId + ", carId=" + carId + "]";
	}


	public Long getFeedbackId() {
		return feedbackId;
	}


	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}


	public String getFeedbackDate() {
		return feedbackDate;
	}


	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Long getCarId() {
		return carId;
	}


	public void setCarId(Long carId) {
		this.carId = carId;
	}
}
