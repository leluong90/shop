package com.ra.service;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.ReviewRequest;
import com.ra.model.entity.Review;
import com.ra.model.entity.User;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest reviewRequest , User user ) throws ProductException;
    List<Review> getAllReview (Long productId) ;
}
