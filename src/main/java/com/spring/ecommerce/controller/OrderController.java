package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }


    /*@PostMapping("/add/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        return orderService.placeOrder(userId);
    }*/
    @PostMapping("/add")
    public Order placeOrder() {
        return orderService.placeOrder();
    }


    @GetMapping("/{userId}")
    public List<Order> getAllOrdersByUser(@PathVariable Long userId) {
        return orderService.getAllOrdersByUser(userId);
    }


    @GetMapping("/details/{orderId}")
    public Order getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderDetails(orderId);
    }

}
