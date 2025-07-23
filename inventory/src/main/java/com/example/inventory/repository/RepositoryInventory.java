package com.example.inventory.repository;

import com.example.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryInventory extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByInventoryName(String inventoryName);
}
