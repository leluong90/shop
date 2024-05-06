package com.ra.service.ipml;

import com.ra.exception.ProductException;
import com.ra.model.dto.request.AddItemRequest;
import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.repository.CartRepository;
import com.ra.service.CartItemService;
import com.ra.service.CartService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository ;
    @Autowired
    private CartItemService cartItemService ;
    @Autowired
    private ProductService productService ;

    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findById(addItemRequest.getId());
        CartItem isPresent = cartItemService.isCartItemExist(cart , product , addItemRequest.getSize() , userId);

        if (isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(addItemRequest.getQuantity());
            cartItem.setUserId(userId);
            int price = addItemRequest.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(addItemRequest.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);

            cart.getCartItems().add(createdCartItem);
        }

        return "Item Add To Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0 ;
        int totalDiscountedPrice = 0 ;
        int totalItem = 0 ;

        for (CartItem cartItem: cart.getCartItems()){
            totalPrice= totalPrice+ cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice((double) totalPrice);
        cart.setDiscounted(totalPrice - totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
