package com.shruteekaTech.serviceImp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shruteekaTech.entity.Product;
import com.shruteekaTech.repository.ProductRepository;
import com.shruteekaTech.service.ProductService;
import com.shruteekaTech.utils.ExcelToListHelper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public void saveProduct(MultipartFile file) {
		
		try 
		{
			// Getting list of products from ExcelToListHelper class by passing file
			List<Product> products = ExcelToListHelper.excelToList(file.getInputStream());
			
			
			
			products.removeIf((p)->p.getProductId()==null || p.getProductName().isEmpty() || p.getProductName()==null 
					|| p.getProductDesc()==null || p.getProductDesc().isEmpty() 
					|| p.getProductPrice()==null ||p.getProductPrice()==0 || p.getProductPrice()<0.0);
			
			
			// Save list of products into DB by saveAll()
			this.productRepo.saveAll(products);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> findAll = this.productRepo.findAll();
		return findAll;
	}
	
	
}
