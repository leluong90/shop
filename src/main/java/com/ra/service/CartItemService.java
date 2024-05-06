package com.ra.service;

import com.ra.exception.CartItemException;
import com.ra.exception.UserException;
import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId ,Long id , CartItem cartItem ) throws CartItemException, UserException ;

    CartItem isCartItemExist (Cart cart , Product product , String size , Long userId ) ;

    void removeCartItem (Long userId , Long cartItem  )throws CartItemException , UserException ;

    CartItem findCartItemById (Long cartItemId) throws CartItemException ;

}
