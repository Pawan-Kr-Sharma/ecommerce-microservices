package com.pk.ecommerce.inventory.mapper;

import com.pk.ecommerce.inventory.dto.InventoryRequest;
import com.pk.ecommerce.inventory.dto.InventoryResponse;
import com.pk.ecommerce.inventory.entity.Inventory;

import lombok.Data;

@Data
public class InventoryMapper {

	public Inventory toEntity(InventoryRequest request) {
		
		Inventory inventory = new Inventory();
		inventory.setSkuCode(request.getSkuCode());
		inventory.setQuantity(request.getQuantity());
		return inventory;
	}
	
	public InventoryResponse toDto(Inventory inventory) {
		return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0, inventory.getQuantity());
	}
	
}
