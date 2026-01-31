package com.product.mgmt.service;

import java.util.List;

import com.product.mgmt.repository.dto.ProductDto;

public interface ProductService {

	void addProduct(ProductDto productDto);

	ProductDto getProduct(String productName);

	void deleteProduct(String productName);

	List<ProductDto> getAllProducts();

}
