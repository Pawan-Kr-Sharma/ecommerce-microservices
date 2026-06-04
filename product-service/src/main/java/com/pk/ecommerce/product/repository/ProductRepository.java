package com.pk.ecommerce.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pk.ecommerce.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByCategoryId(Long CategoryId, Pageable pageable);
	
	Page<Product> findByPriceBetween(Double min, Double max, Pageable pageable);
	
	Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
	
	@Query("SELECT p from Product p "+
	"Where LOWER(p.name) LIKE LOWER(CONCATE('%', :keyword,'%'))" +
			"OR LOWER(p.description) LIKE LOWER(CONCATE('%', :keyword,'%'))")
	Page<Product> searchProduct(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT p from Product p "+
	"WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword,'%'))) " +
			"AND(:category IS NULL OR p.category.id= :categoryId) " +
	"AND (p.price BETWEEN :minPrice AND :maxPrice)")
	Page<Product> advancefilter(
			@Param("keyword") String keyword,
			@Param("categoryId") Long categoryId,
			@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice,
			Pageable pageable
			);
}
