package com.ra.service;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.RatingRequest;
import com.ra.model.entity.Rating;
import com.ra.model.entity.User;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest ratingRequest , User user ) throws ProductException ;
    public List<Rating> getProductsRating(Long productId) ;
}
