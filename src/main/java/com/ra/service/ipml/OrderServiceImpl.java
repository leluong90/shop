package com.ra.service.ipml;

import com.ra.exception.OrderException;
import com.ra.model.entity.Address;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.repository.CartRepository;
import com.ra.service.CartService;
import com.ra.service.OrderService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartRepository cartRepository ;
    @Autowired
    private CartService cartItemService;
    @Autowired
    private ProductService productService;

    public OrderServiceImpl(CartRepository cartRepository , CartService cartItemService , ProductService productService){
        this.cartRepository = cartRepository ;
        this.cartItemService = cartItemService ;
        this.productService = productService ;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long id) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long id) {
        return null;
    }

    @Override
    public Order placedOrder(Long id) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long id) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long id) throws OrderException {
        return null;
    }

    @Override
    public Order deliveryOrder(Long id) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long id) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long id) throws OrderException {

    }
}
