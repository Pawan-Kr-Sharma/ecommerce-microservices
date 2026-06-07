package com.pk.ecommerce.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.ecommerce.product.dto.CategoryDto;
import com.pk.ecommerce.product.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCateory(@RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCateory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCateory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted successfully");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCateory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategory(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCateory() {
		return ResponseEntity.ok(categoryService.getAll());
	}
}
