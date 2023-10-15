package com.example.orderservice.repository;

import com.example.orderservice.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUserId(Long userId);
}
