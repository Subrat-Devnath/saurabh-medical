package com.product.mgmt.service;

import com.product.mgmt.repository.dto.ProductDto;

public interface ProductService {

	void addProduct(ProductDto productDto);

	ProductDto getProduct(String productName);

	void deleteProduct(String productName);

}
