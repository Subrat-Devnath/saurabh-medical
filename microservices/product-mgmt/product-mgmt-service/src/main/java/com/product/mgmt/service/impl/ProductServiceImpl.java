package com.product.mgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dto.ProductDto;
import com.product.mgmt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(ProductDto productDto) {
		productRepository.addProduct(productDto);
	}

	@Override
	public ProductDto getProduct(String productName) {
		return productRepository.getProduct(productName);
	}
	
	@Override
	public void deleteProduct(String productName) {
		productRepository.deleteProduct(productName);
	}

}
