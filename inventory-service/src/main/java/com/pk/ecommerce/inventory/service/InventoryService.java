package com.pk.ecommerce.inventory.service;

import com.pk.ecommerce.inventory.dto.InventoryRequest;
import com.pk.ecommerce.inventory.dto.InventoryResponse;
import com.pk.ecommerce.inventory.event.OrderPlaceEvent;

public interface InventoryService {

	InventoryResponse checkInventory(String skuCode);
	public void addInventory(InventoryRequest request);
	void updateStock(OrderPlaceEvent event);
}
