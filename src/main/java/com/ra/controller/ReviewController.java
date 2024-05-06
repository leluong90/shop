package com.ra.controller;

import com.ra.exception.ProductException;
import com.ra.exception.UserException;
import com.ra.model.dto.request.ReviewRequest;
import com.ra.model.entity.Review;
import com.ra.model.entity.User;
import com.ra.service.ReviewService;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req ,
                                               @RequestHeader("Authorization") String jwt ) throws ProductException, UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req , user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId ){
        List<Review> reviews = reviewService.getAllReview(productId);
        return new ResponseEntity<>(reviews , HttpStatus.CREATED);
    }
}
