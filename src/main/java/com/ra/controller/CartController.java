package com.ra.controller;

import com.ra.exception.ProductException;
import com.ra.exception.UserException;
import com.ra.model.dto.request.AddItemRequest;
import com.ra.model.dto.response.ApiResponse;
import com.ra.model.entity.Cart;
import com.ra.model.entity.User;
import com.ra.service.CartService;
import com.ra.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
//@Tag(name = "Cart Management" , description = "find user cart , add item to cart")
public class CartController {
    @Autowired
    private CartService cartService ;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(description = "find cart by user id")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt  ) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @PutMapping("/add")
    @Operation(description = "add item to cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req
            , @RequestHeader("Authorization") String jwt  ) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart");
        res.setStatus(true);

        return new ResponseEntity<>(res , HttpStatus.OK) ;

    }




}
