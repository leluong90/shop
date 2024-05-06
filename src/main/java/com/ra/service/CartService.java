package com.ra.service;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.AddItemRequest;
import com.ra.model.entity.Cart;
import com.ra.model.entity.User;

public interface CartService {
    Cart createCart(User user);

    String addCartItem(Long userId , AddItemRequest addItemRequest  ) throws ProductException;

    Cart findUserCart(Long userId);


}
