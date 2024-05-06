package com.ra.service;

import com.ra.exception.OrderException;
import com.ra.model.entity.Address;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;

import java.util.List;

public interface OrderService {
    Order createOrder (User user , Address shippingAddress);
    Order findOrderById(Long id) throws OrderException;
    List<Order> usersOrderHistory(Long id);

    Order placedOrder(Long id) throws OrderException;

    Order confirmedOrder(Long id) throws OrderException;

    Order shippedOrder (Long id) throws OrderException;

    Order deliveryOrder (Long id) throws OrderException;

    Order canceledOrder (Long id) throws OrderException;

    List<Order> getAllOrders();

    void deleteOrder(Long id) throws OrderException;
}
