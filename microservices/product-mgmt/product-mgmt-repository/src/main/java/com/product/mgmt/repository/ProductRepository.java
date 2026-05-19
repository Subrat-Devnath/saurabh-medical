package com.product.mgmt.repository;

import com.common.service.dtos.PaginationCriteria;
import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.repository.dto.ProductPageResponse;

import java.util.List;

public interface ProductRepository {

    void addProduct(ProductDTO productDto);

    ProductDTO getProduct(String productName);

    List<ProductDTO> searchProduct(String productName);

    void deleteProduct(String productName);

    List<ProductDTO> getAllProducts();

    ProductPageResponse getProductsByOrganizationId(String organizationId, Integer pageSize, String pageState);
}
