package com.pk.ecommerce.inventory.service;

import com.pk.ecommerce.inventory.dto.InventoryRequest;
import com.pk.ecommerce.inventory.dto.InventoryResponse;

public interface InventoryService {

	InventoryResponse checkInventory(String skuCode);
	public void addInventory(InventoryRequest request);
}
