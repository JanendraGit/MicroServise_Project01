package com.example.inventory.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseInventoryList {
    List<ResponseInventory> responseInventoryList;
}
