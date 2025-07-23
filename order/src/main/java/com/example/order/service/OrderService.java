package com.example.order.service;

import com.example.order.Exception.OrderException;
import com.example.order.controller.request.CreateOrderRequest;
import com.example.order.controller.request.UpdateOrderRequest;
import com.example.order.controller.response.OrderResponse;

import java.util.List;

public interface OrderService {
    void addOrder(CreateOrderRequest createRequest)throws OrderException;
    void updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest)throws OrderException;
    void deleteOrder(Long orderId)throws OrderException;
    OrderResponse getOrderById(Long orderId)throws OrderException;
    List<OrderResponse> findAllOrders()throws OrderException;
}
