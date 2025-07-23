package com.example.order.service.impl;

import com.example.inventory.controller.response.ResponseInventory;
import com.example.order.Exception.OrderException;
import com.example.order.controller.request.CreateOrderRequest;
import com.example.order.controller.request.UpdateOrderRequest;
import com.example.order.controller.response.OrderResponse;
import com.example.order.domain.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ServiceImpl implements OrderService {

    private final WebClient InventoryWebClient;
    private OrderRepository orderRepository;

    public ServiceImpl(WebClient InventoryWebClient, OrderRepository orderRepository) {
        this.InventoryWebClient = InventoryWebClient;
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(CreateOrderRequest createRequest)throws OrderException {

        Long productId = createRequest.getProductId();
        try{
            ResponseInventory responseInventory= InventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("inventory/{id}").build(productId))
                    .retrieve()
                    .bodyToMono(ResponseInventory.class)
                    .block();

            if (responseInventory.getQuantity() > 0){
                Order order = new Order();
                order.setOrderName(createRequest.getOrderName());
                order.setProductId(createRequest.getProductId());
                order.setOrderPrice(createRequest.getOrderPrice());
                order.setOrderQuantity(createRequest.getOrderQuantity());
                orderRepository.save(order);
            }else {
                throw new OrderException("Item is not available in the inventory. Please update the inventory");
            }
        }catch (WebClientResponseException e){
            if(e.getStatusCode().is5xxServerError()){
                throw new OrderException("500 error its mean Server Error");
            }
            throw new OrderException("Product not found");
        }


//        Optional<Order> optionalOrder = orderRepository.findByOrderName(createRequest.getOrderName());
//        if (optionalOrder.isPresent()) {
//            throw new OrderException("Order already exists");
//        }

    }

    @Override
    public void updateOrder(Long orderId, UpdateOrderRequest  updateOrderRequest)throws OrderException {
        Order order = orderRepository.findById(updateOrderRequest.getOrderId())
                .orElseThrow(() -> new OrderException("Order not found"));
        order.setOrderName(updateOrderRequest.getOrderName());
        order.setOrderPrice(updateOrderRequest.getOrderPrice());
        order.setOrderQuantity(updateOrderRequest.getOrderQuantity());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setOrderName(order.getOrderName());
        orderResponse.setOrderPrice(order.getOrderPrice());
        orderResponse.setOrderQuantity(order.getOrderQuantity());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderName(order.getOrderName())
                .orderQuantity(order.getOrderQuantity())
                .build()).collect(Collectors.toList());
        return orderResponseList;
    }

}
