package com.inventory.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.productservice.model.Product;
import com.inventory.productservice.repository.ProductItemRepository;

@Service
public class ProductItemService {
	@Autowired
	ProductItemRepository productItemRepository;
	
	public Product save(Product product){
		return productItemRepository.save(product);
	}
	
	public Product getByItemName(String itemName){
		return productItemRepository.findByItemName(itemName);
	}
	
	public void deleteByItemName(String itemName){
		productItemRepository.deleteByItemName(itemName);
	}
	
	public List<Product> getList(){
		return (List<Product>) productItemRepository.findAll();
	}
}
