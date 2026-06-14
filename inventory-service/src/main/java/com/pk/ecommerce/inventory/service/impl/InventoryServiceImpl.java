package com.pk.ecommerce.inventory.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.inventory.dto.InventoryRequest;
import com.pk.ecommerce.inventory.dto.InventoryResponse;
import com.pk.ecommerce.inventory.entity.Inventory;
import com.pk.ecommerce.inventory.entity.ProcessedOrder;
import com.pk.ecommerce.inventory.event.OrderPlaceEvent;
import com.pk.ecommerce.inventory.exception.InventoryNotFoundException;
import com.pk.ecommerce.inventory.exception.InventoryOutOfStockException;
import com.pk.ecommerce.inventory.mapper.InventoryMapper;
import com.pk.ecommerce.inventory.repository.InventoryRepository;
import com.pk.ecommerce.inventory.repository.ProcessedOrderRepository;
import com.pk.ecommerce.inventory.service.InventoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

	private final InventoryRepository inventoryRepository;
	private final InventoryMapper inventoryMapper;
	private final ProcessedOrderRepository processedOrderRepository;
	
	public InventoryResponse checkInventory(String skuCode) {
		log.info("search inventory by skuCode: {}",skuCode);
		Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new InventoryNotFoundException(skuCode));
		return inventoryMapper.toDto(inventory);
	}
	
	public void addInventory(InventoryRequest request) {
		log.info("adding inventory..");
		Inventory inventory = inventoryMapper.toEntity(request);
		inventoryRepository.save(inventory);
	}

	@Override
	@Transactional
	public void updateStock(OrderPlaceEvent event) {
		
		//idempotency 
		if(processedOrderRepository.existsByOrderId(event.getOrderId())) {
			return;
		}
		Inventory inventory = inventoryRepository.findBySkuCode(event.getSkuCode()).orElseThrow(() -> new InventoryNotFoundException(event.getSkuCode()));
		Integer availableQuantity = inventory.getQuantity();
		Integer orderQuantity = event.getQuantity();
		
		if(availableQuantity < orderQuantity) {
			throw new InventoryOutOfStockException("Insufficient stock for this skuCode");
		}
		
		inventory.setQuantity(availableQuantity - orderQuantity);
		inventoryRepository.save(inventory);
		
		ProcessedOrder processed = new ProcessedOrder();
		processed.setOrderId(event.getOrderId());
		processed.setProcessedAt(LocalDateTime.now());
		
		processedOrderRepository.save(processed);
	}
}
