package com.pk.ecommerce.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pk.ecommerce.product.dto.CategoryDto;
import com.pk.ecommerce.product.entity.Category;
import com.pk.ecommerce.product.exception.ResourceNotFoundException;
import com.pk.ecommerce.product.mapper.CategoryMapper;
import com.pk.ecommerce.product.repository.CategoryRepository;
import com.pk.ecommerce.product.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	private final CategoryMapper categoryMapper;
	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		//log.info("Category saving...");
		//Category category = categoryRepository.save(categoryMapper.toEntity(dto));
		
		//log.info("category saved successfully");
		return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(dto)));
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto dto) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id"));
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		category.setParentId(dto.getParentId());
		
		//categoryRepository.save(category);
		return categoryMapper.toDto(categoryRepository.save(category));
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id"));
		categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategory(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id"));
		return categoryMapper.toDto(category);
	}

	@Override
	public List<CategoryDto> getAll() {
		/*List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> categoryMapper.toDto(category)).toList();*/
		return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
	}

}
