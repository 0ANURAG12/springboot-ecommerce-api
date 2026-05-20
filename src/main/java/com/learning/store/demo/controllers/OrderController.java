package com.learning.store.demo.controllers;


import com.learning.store.demo.dto.OrderRequest;
import com.learning.store.demo.entities.Order;
import com.learning.store.demo.services.OrderService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController{

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    // Get order by id
    @GetMapping("{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
}




























