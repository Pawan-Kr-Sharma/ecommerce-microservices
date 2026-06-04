package com.pk.ecommerce.product.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pk.ecommerce.product.dto.ProductDto;
import com.pk.ecommerce.product.entity.Category;
import com.pk.ecommerce.product.entity.Product;
import com.pk.ecommerce.product.exception.ResourceNotFoundException;
import com.pk.ecommerce.product.mapper.ProductMapper;
import com.pk.ecommerce.product.repository.ProductRepository;
import com.pk.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	@Override
	public ProductDto createProduct(ProductDto dto) {
		log.info("saving product");
		
		Category category = null;
		if(dto.getCategoryId() != null) {
			category = new Category();
			category.setId(dto.getId());
		}
		
		//Product product = productMapper.toEntity(dto, category);
		Product product = productRepository.save(productMapper.toEntity(dto, category));
		log.info("product saved successfully");
		
		return productMapper.toDto(product);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto dto) {
		log.info("updating product id: {}", id);
		
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id: "+ id));
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setDiscountPrice(dto.getDiscountPrice());
		product.setQuantity(dto.getQuantity());
		product.setBrand(dto.getBrand());
		product.setImageUrl(dto.getImageUrl());
		
		if(dto.getCategoryId() != null) {
			
			Category category = new Category();
			category.setId(dto.getId());
			
			product.setCategory(category);
		}
		
		log.info("updated product successfully id: {}", id);
		//productRepository.save(product);
		return productMapper.toDto(productRepository.save(product));
	}

	@Override
	public void deleteProduct(Long id) {
		log.info("deleting product id: {}", id);
		
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id: "+ id));
		productRepository.delete(product);
		log.info("deleted successfully product id: {}", id);
	}

	@Override
	public ProductDto getProduct(Long id) {
		log.info("deleting product id: {}", id);
		
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id: "+ id));
		return productMapper.toDto(product);
	}

	@Override
	public Page<ProductDto> getAllProduct(int page, int size, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		
		Page<Product> productPage = productRepository.findAll(pageable);
		//productPage.map(product -> productMapper.toDto(product));
		
		return productPage.map(productMapper::toDto);
	}

	@Override
	public Page<ProductDto> searchProduct(String keyword, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = productRepository.searchProduct(keyword, pageable);
		return productPage.map(productMapper::toDto);
	}

	@Override
	public Page<ProductDto> filterProduct(Long categoryId, Double minPrice, Double maxPrice, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = productRepository.advancefilter(null, categoryId, minPrice, maxPrice, pageable);
		return productPage.map(productMapper::toDto);
	}

	@Override
	public Page<ProductDto> advanceFilter(String keyword, Long categoryId, Double minPrice, Double maxPrice, int page,
			int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = productRepository.advancefilter(keyword, categoryId, minPrice, maxPrice, pageable);
		return productPage.map(productMapper::toDto);
	}

}
