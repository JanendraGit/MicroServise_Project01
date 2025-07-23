package com.example.order.repository;

import com.example.order.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByOrderName(String orderName);
}
