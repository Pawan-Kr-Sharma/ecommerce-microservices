package com.pk.ecommerce.inventory.service.impl;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.inventory.dto.InventoryRequest;
import com.pk.ecommerce.inventory.dto.InventoryResponse;
import com.pk.ecommerce.inventory.entity.Inventory;
import com.pk.ecommerce.inventory.exception.InventoryNotFoundException;
import com.pk.ecommerce.inventory.mapper.InventoryMapper;
import com.pk.ecommerce.inventory.repository.InventoryRepository;
import com.pk.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

	private final InventoryRepository inventoryRepository;
	private final InventoryMapper inventoryMapper;
	
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
}
