package com.pk.ecommerce.product.service;

import java.util.List;

import com.pk.ecommerce.product.dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto dto);
	CategoryDto updateCategory(Long id, CategoryDto dto);
	void deleteCategory(Long id);
	CategoryDto getCategory(Long id);
	List<CategoryDto> getAll();
}
