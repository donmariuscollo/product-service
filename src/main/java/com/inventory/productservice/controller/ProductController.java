package com.inventory.productservice.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.productservice.model.Product;
import com.inventory.productservice.service.ProductItemService;

@Produces("application/json")
@Consumes("application/json")
@RestController
public class ProductController {
	@Autowired
	ProductItemService productItemService;
	
	@PostMapping("/api/product")
	public ResponseEntity<Product> save(@RequestBody Product product){

		Product searchProduct=productItemService.getByItemName(product.getItemName());
		
		Product retProduct=null;
		
		if (searchProduct==null)
			retProduct=productItemService.save(product);
		else {
			product.setId(searchProduct.getId());
			retProduct=productItemService.save(product);
			return new ResponseEntity<Product>(retProduct,HttpStatus.OK);
		}

		return new ResponseEntity<Product>(retProduct,HttpStatus.CREATED);
	}

	
	
	@GetMapping("/api/product/{itemName}")
	public ResponseEntity<Product> getProduct(@PathVariable String itemName){

		Product product=productItemService.getByItemName(itemName);
		
		if (product==null)
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}

	@DeleteMapping("/api/product/{itemName}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String itemName){

		Product product=productItemService.getByItemName(itemName);
		
		if (product==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		else
			productItemService.deleteByItemName(itemName);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/api/products")
	public ResponseEntity<List<Product>> getList(){
		List<Product> list=productItemService.getList();
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	
}
