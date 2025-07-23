package com.example.order.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderListResponse {
    List<OrderResponse> orderResponses;
}
