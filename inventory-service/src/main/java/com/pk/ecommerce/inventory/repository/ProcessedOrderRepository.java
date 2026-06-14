package com.pk.ecommerce.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.ecommerce.inventory.entity.ProcessedOrder;

public interface ProcessedOrderRepository extends JpaRepository<ProcessedOrder, Long>{

	boolean existsByOrderId(String orderId);
}
