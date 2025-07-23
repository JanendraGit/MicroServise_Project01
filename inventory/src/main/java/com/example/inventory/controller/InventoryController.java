package com.example.inventory.controller;

import com.example.inventory.Service.impl.ImplInventory;
import com.example.inventory.controller.request.RequestInventory;
import com.example.inventory.controller.response.ResponseInventory;
import com.example.inventory.exception.InventoryException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class InventoryController {

    private ImplInventory implInventory;

    @PostMapping(value = "/inventory")
    public void addInventory(@RequestBody RequestInventory requestInventory) throws InventoryException {
        implInventory.addInventory(requestInventory);
    }

    @GetMapping(value = "/inventory/{id}")
    public ResponseInventory getInventory(@RequestParam Long id) throws InventoryException {
        return implInventory.getInventory(id);
    }

    @GetMapping(value = "/inventory")
    public List<ResponseInventory> getInventoryList() throws InventoryException {
        return implInventory.findAllInventory();
    }

    @PutMapping(value = "/inventory/{id}")
    public void updateInventory(@RequestBody RequestInventory requestInventory, @PathVariable Long id) throws InventoryException {
        implInventory.updateInventory(id, requestInventory);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInventory(@PathVariable Long id) throws InventoryException {
        implInventory.deleteInventory(id);
    }

    @PutMapping(value = "/inventory/quantity/{id}")
    public void updateInventoryQuantity(@RequestBody Integer quantity, @PathVariable Long id) throws InventoryException {
        implInventory.updateInventoryQuantity(id, quantity);
    }

    @PutMapping(value = "/inventory/productName/{id}")
    public void updateInventoryProductName(@RequestBody String productName, @PathVariable Long id) throws InventoryException {
        implInventory.updateInventoryProductName(id, productName);
    }

    @PutMapping(value = "/inventory/inventoryName/{id}")
    public void updateInventoryInventoryName(@RequestBody String inventoryName, @PathVariable Long id) throws InventoryException {
        implInventory.updateInventoryName(id, inventoryName);
    }
}
