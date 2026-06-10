package com.pk.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.ecommerce.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
