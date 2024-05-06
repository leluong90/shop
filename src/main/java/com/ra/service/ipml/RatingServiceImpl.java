package com.ra.service.ipml;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.RatingRequest;
import com.ra.model.entity.Product;
import com.ra.model.entity.Rating;
import com.ra.model.entity.User;
import com.ra.repository.RatingRepository;
import com.ra.service.ProductService;
import com.ra.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository ;
    @Autowired
    private ProductService productService ;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest ratingRequest, User user) throws ProductException {
        Product product = productService.findById(ratingRequest.getId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(ratingRequest.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
