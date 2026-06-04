package com.pk.ecommerce.product.mapper;

import com.pk.ecommerce.product.dto.CategoryDto;
import com.pk.ecommerce.product.entity.Category;

public class CategoryMapper {

	public CategoryDto toDto(Category category) {

		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.ParentId(category.getParentId())
				.build();
	}
	
	public Category toEntity(CategoryDto dto) {
		
		return Category.builder()
				.name(dto.getName())
				.description(dto.getDescription())
				.parentId(dto.getParentId())
				.build();
	}
}
