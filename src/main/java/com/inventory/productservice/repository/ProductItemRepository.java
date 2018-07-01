package com.inventory.productservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.productservice.model.Product;

@Repository
public interface ProductItemRepository extends CrudRepository<Product,Long>{
	
	public Product findByItemName(String itemName);
	
	@Transactional
	public void deleteByItemName(String itemName);
	
}
