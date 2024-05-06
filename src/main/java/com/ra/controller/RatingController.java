package com.ra.controller;

import com.ra.exception.ProductException;
import com.ra.exception.UserException;
import com.ra.model.dto.request.RatingRequest;
import com.ra.model.entity.Rating;
import com.ra.model.entity.User;
import com.ra.service.RatingService;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService ;
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req ,
                                               @RequestHeader("Authorization") String jwt ) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(req,user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);

    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId ,
                                                          @RequestHeader ("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Rating> ratings = ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.CREATED);
    }
}
