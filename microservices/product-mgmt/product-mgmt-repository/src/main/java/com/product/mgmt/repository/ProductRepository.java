package com.product.mgmt.repository;

import java.util.List;
import java.util.UUID;

import com.common.service.dtos.Pageable;
import com.product.mgmt.repository.entity.ProductEntity;

import org.springframework.web.bind.annotation.RequestBody;

import com.product.mgmt.repository.dto.ProductDto;

public interface ProductRepository {

    void addProduct(@RequestBody ProductDto productDto);

    ProductDto getProduct(String productName);

    void deleteProduct(String productName);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsWithPagination(Pageable pageable);

}
