package com.example.inventory.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseInventory {
    private Long inventoryId;
    private String inventoryName;
    private Integer quantity;
    private String productName;
    private String productDescription;
    private Integer productPrice;
}
