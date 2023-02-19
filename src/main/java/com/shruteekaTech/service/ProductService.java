package com.shruteekaTech.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shruteekaTech.entity.Product;

public interface ProductService {
	
	public void saveProduct(MultipartFile file);
	
	public List<Product> getAllProducts();
	
	

}
