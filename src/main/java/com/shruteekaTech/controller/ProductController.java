package com.shruteekaTech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shruteekaTech.entity.Product;
import com.shruteekaTech.service.ProductService;
import com.shruteekaTech.utils.ApiResponse;
import com.shruteekaTech.utils.ExcelToListHelper;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse> uploadExcelFile( @RequestParam ("file") MultipartFile file){
		if(ExcelToListHelper.checkValidExcelFile(file)) {
			this.productService.saveProduct(file);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Saved", true),HttpStatus.CREATED);
		}else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Upload excel file", false),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> allProducts = this.productService.getAllProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
	
	
	
}
