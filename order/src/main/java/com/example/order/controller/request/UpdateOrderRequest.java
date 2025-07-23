package com.example.order.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderRequest {
    private Long orderId;
    private String orderName;
    private Double orderPrice;
    private Integer orderQuantity;
}
