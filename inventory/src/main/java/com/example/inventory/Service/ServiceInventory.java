package com.example.inventory.Service;

import com.example.inventory.controller.request.RequestInventory;
import com.example.inventory.controller.response.ResponseInventory;
import com.example.inventory.controller.response.ResponseInventoryList;
import com.example.inventory.exception.InventoryException;

import java.util.List;

public interface ServiceInventory {
    void addInventory(RequestInventory requestInventory)throws InventoryException;
    void updateInventory(long id,RequestInventory requestInventory)throws InventoryException;
    void deleteInventory(long id)throws InventoryException;
    ResponseInventory getInventory(long id)throws InventoryException;
    void updateInventoryName(Long id, String inventoryName)throws InventoryException;
    void updateInventoryQuantity(Long id, int quantity)throws InventoryException;
    void updateInventoryProductName(Long id, String productName)throws InventoryException;
    List<ResponseInventory> findAllInventory();
}
