package com.example.inventory.Service.impl;

import com.example.inventory.Service.ServiceInventory;
import com.example.inventory.controller.request.RequestInventory;
import com.example.inventory.controller.response.ResponseInventory;
import com.example.inventory.controller.response.ResponseInventoryList;
import com.example.inventory.domain.Inventory;
import com.example.inventory.exception.InventoryException;
import com.example.inventory.repository.RepositoryInventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImplInventory implements ServiceInventory {
    private final RepositoryInventory repositoryInventory;

    @Override
    public void addInventory(RequestInventory requestInventory)throws InventoryException {
        Optional<Inventory> inventory = repositoryInventory.findByInventoryName(requestInventory.getInventoryName());
        if(inventory.isPresent()){
            throw new InventoryException("Inventory already exists");
        }
        Inventory inventory1 = new Inventory();
        inventory1.setInventoryName(requestInventory.getInventoryName());
        inventory1.setQuantity(requestInventory.getQuantity());
        inventory1.setProductName(requestInventory.getProductName());
        inventory1.setProductPrice(requestInventory.getProductPrice());
        inventory1.setProductDescription(requestInventory.getProductDescription());
        repositoryInventory.save(inventory1);
    }

    @Override
    public void updateInventory(long id, RequestInventory requestInventory) {
        Inventory inventory = repositoryInventory.findByInventoryName(requestInventory.getInventoryName())
                .orElseThrow(()-> new InventoryException("inventory already exists"));
        inventory.setQuantity(requestInventory.getQuantity());
        inventory.setProductName(requestInventory.getProductName());
        inventory.setProductPrice(requestInventory.getProductPrice());
        inventory.setProductDescription(requestInventory.getProductDescription());
        inventory.setInventoryName(requestInventory.getInventoryName());
        repositoryInventory.save(inventory);
    }

    @Override
    public void deleteInventory(long id) {
        Inventory inventory = repositoryInventory.findById(id)
                .orElseThrow(()-> new InventoryException("inventory not found"));
        repositoryInventory.delete(inventory);
    }

    @Override
    public ResponseInventory getInventory(long id) {
        Inventory inventory = repositoryInventory.findById(id)
                .orElseThrow(()-> new InventoryException("inventory not found"));
        ResponseInventory responseInventory = new ResponseInventory();
        responseInventory.setInventoryId(inventory.getInventoryId());
        responseInventory.setQuantity(inventory.getQuantity());
        responseInventory.setProductName(inventory.getProductName());
        responseInventory.setProductPrice(inventory.getProductPrice());
        responseInventory.setProductDescription(inventory.getProductDescription());
        return  responseInventory;
    }

    @Override
    public void updateInventoryName(Long id, String inventoryName) {
        Inventory inventory = repositoryInventory.findById(id)
                .orElseThrow(()-> new InventoryException("inventory not found"));
        inventory.setInventoryName(inventoryName);
    }

    @Override
    public void updateInventoryQuantity(Long id, int quantity) {
        Inventory inventory = repositoryInventory.findById(id)
                .orElseThrow(()-> new InventoryException("inventory not found"));
        inventory.setQuantity(quantity);
    }

    @Override
    public void updateInventoryProductName(Long id, String productName) {
        Inventory inventory = repositoryInventory.findById(id)
                .orElseThrow(()-> new InventoryException("inventory not found"));
        inventory.setProductName(productName);
    }

    @Override
    public List<ResponseInventory> findAllInventory() {
        List<Inventory>  inventorys = repositoryInventory.findAll();
        List<ResponseInventory> responseInventory =inventorys.stream().map(inventory -> ResponseInventory.builder()
                .inventoryId(inventory.getInventoryId())
                .inventoryName(inventory.getInventoryName())
                .productDescription(inventory.getProductDescription())
                .productPrice(inventory.getProductPrice())
                .productName(inventory.getProductName()).build()).collect(Collectors.toList());
        return responseInventory;
    }
}
