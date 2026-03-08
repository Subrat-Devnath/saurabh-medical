package com.product.mgmt.repository;

import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;

import java.util.List;

public interface ProductPurchaceHistoryRepository {

    List<ProductPurchaseHistoryDTO> getProductPurchaceHistory(String productName);

    List<ProductPurchaseHistoryDTO> getProductQuantities(List<String> productName);
}
