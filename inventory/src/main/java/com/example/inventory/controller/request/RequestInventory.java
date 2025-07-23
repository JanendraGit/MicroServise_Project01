package com.example.inventory.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInventory {
    private String inventoryName;
    private Integer quantity;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Long productId;
}
