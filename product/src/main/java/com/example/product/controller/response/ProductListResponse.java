package com.example.product.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductListResponse {
    List<ProductResponse> products;
}
