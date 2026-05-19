package com.product.mgmt.repository;

import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.repository.dto.ProductPageResponse;

import java.util.List;

public interface ProductCustomRepository {
    ProductPageResponse getProducts(
            String organizationId,
            Integer pageSize,
            String pageState
    );
}
