package com.learning.store.demo.services;

import com.learning.store.demo.dto.OrderItemRequest;
import com.learning.store.demo.dto.OrderRequest;
import com.learning.store.demo.entities.Order;
import com.learning.store.demo.entities.OrderItem;
import com.learning.store.demo.entities.Product;
import com.learning.store.demo.repositories.OrderRepository;
import com.learning.store.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest){

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest: orderRequest.getItems()){

            Product product = productRepository.findById(
                    itemRequest.getProductId()
            ).orElseThrow(()-> new RuntimeException("Product not found with id "+ itemRequest.getProductId()));

            // Check the product stock
            if(product.getStockQuantity()<itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for "+ itemRequest);
            }

            // Calculate total price
            BigDecimal priceOfItem = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(priceOfItem);

            // Update the product table with latest stock quantity
            product.setStockQuantity(
                    product.getStockQuantity() - itemRequest.getQuantity()
            );
            productRepository.save(product);

            // Builder pattern to make obj
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();
            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

    // Get all orders
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    // Get order by id
    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found with id "+id));
    }
}