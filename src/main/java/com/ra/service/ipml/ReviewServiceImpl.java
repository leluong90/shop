package com.ra.service.ipml;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.ReviewRequest;
import com.ra.model.entity.Product;
import com.ra.model.entity.Review;
import com.ra.model.entity.User;
import com.ra.repository.ProductRepository;
import com.ra.repository.ReviewRepository;
import com.ra.service.ProductService;
import com.ra.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository ;
    @Autowired
    private ProductService productService ;
    @Autowired
    private ProductRepository productRepository ;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = productService.findById(reviewRequest.getId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(reviewRequest.getReview());
        review.setCreatedAt(LocalDateTime.now());


        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductReview(productId);
    }
}
