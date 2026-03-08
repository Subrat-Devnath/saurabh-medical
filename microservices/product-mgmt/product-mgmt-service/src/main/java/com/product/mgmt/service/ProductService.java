package com.product.mgmt.service;

import com.common.service.dtos.PaginationCriteria;
import com.product.mgmt.repository.dto.ProductDTO;

import java.util.List;

public interface ProductService {

	void addProduct(ProductDTO productDto);

	ProductDTO getProduct(String productName);

    List<ProductDTO> searchProduct(String productName);

    void deleteProduct(String productName);

	List<ProductDTO> getAllProducts();

	List<ProductDTO> getProductsWithPagination(PaginationCriteria pageable);
}
