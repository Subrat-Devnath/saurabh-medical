package com.product.mgmt.repository;

import org.springframework.web.bind.annotation.RequestBody;

import com.product.mgmt.repository.dto.ProductDto;

public interface ProductRepository {

	public void addProduct(@RequestBody ProductDto productDto);

	public ProductDto getProduct(String productName);

	public void deleteProduct(String productName);

}
