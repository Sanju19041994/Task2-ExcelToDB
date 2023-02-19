package com.shruteekaTech.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.shruteekaTech.entity.Product;

public class ExcelToListHelper {

	public static boolean checkValidExcelFile(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) 
		{
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Product> excelToList(InputStream is){
		
		List<Product> list = new ArrayList<Product>();
		
		try 
		{
			// Getting workbook which uploaded
			XSSFWorkbook workBook = new XSSFWorkbook(is);
			
			// Getting sheet from workbook
			XSSFSheet sheet = workBook.getSheet("data");
			
			int rowNumber = 0;
			
			// Getting rows from sheet
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) 
			{
				Row row = iterator.next();
				
				// no need to take first row (which describe sheet element name)
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				
				// Getting cell from row
				Iterator<Cell> cells = row.iterator();
				
				int cellId = 0;
				
				Product product = new Product();
				
				while(cells.hasNext())
				{
					// Getting data from cells and storing into Product Object
					 Cell cell = cells.next();
					 
					 switch (cellId) 
					 {
					      case 0 :
						      product.setProductId((int)cell.getNumericCellValue());
                                  break;
					      case 1 :
						      product.setProductName(cell.getStringCellValue());
                                  break;
					      case 2 :
						      product.setProductDesc(cell.getStringCellValue());
                                  break;
					      case 3 :
						      product.setProductPrice(cell.getNumericCellValue());
                                  break;
					      default:
//						     throw new IllegalArgumentException("Unexpected value: " + cellId);
						          break;
					}
					 cellId++;
				}
				
					list.add(product);
					
			}	
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
}
