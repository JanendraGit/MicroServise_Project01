package com.example.order.controller;

import com.base.base.dto.OrderEventDto;
import com.example.order.Exception.OrderException;
import com.example.order.controller.request.CreateOrderRequest;
import com.example.order.controller.request.UpdateOrderRequest;
import com.example.order.controller.response.OrderResponse;
import com.example.order.kafka.OrderProduser;
import com.example.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private OrderProduser  orderProduser;

    @PostMapping(value = "/order")
    public void addOrder(@RequestBody CreateOrderRequest createOrderRequest)throws OrderException {
        OrderEventDto orderEventDto = new OrderEventDto();
        orderEventDto.setMessage("order is commited");
        orderEventDto.setStatus("pending");
        orderProduser.sendMessage(orderEventDto);

        orderService.addOrder(createOrderRequest);
    }
    @GetMapping(value = "/order/{order_id}")
    public OrderResponse getOrderById(@PathVariable("order_id") Long orderId)throws OrderException {
        return orderService.getOrderById(orderId);
    }
    @GetMapping(value = "/order")
    public List<OrderResponse> findAllOrders()throws OrderException {
        return orderService.findAllOrders();
    }
    @PutMapping(value = "/order/{order_id}")
    public void updateOrder(Long orderId, UpdateOrderRequest updateOrderRequest)throws OrderException{
    orderService.updateOrder(orderId, updateOrderRequest);
    }
    @DeleteMapping(value = "/{order_id}")
    public void deleteOrder(@PathVariable("order_id") Long orderId)throws OrderException{
        orderService.deleteOrder(orderId);
    }
}

