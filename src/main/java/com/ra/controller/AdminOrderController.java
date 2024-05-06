package com.ra.controller;

import com.ra.exception.OrderException;
import com.ra.model.dto.response.ApiResponse;
import com.ra.model.entity.Order;
import com.ra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt)throws OrderException{
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException{
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.deliveryOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);

    }
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order>cancelOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") Long jwt) throws OrderException {
        Order order = orderService.canceledOrder(orderId);
        return new ResponseEntity<>(order , HttpStatus.OK);
    }
    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder(orderId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Order deleted successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }
}
