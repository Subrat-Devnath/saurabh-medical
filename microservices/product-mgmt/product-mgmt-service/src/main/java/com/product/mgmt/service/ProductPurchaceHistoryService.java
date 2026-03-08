package com.product.mgmt.service;

import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;

import java.util.List;

public interface ProductPurchaceHistoryService {

    List<ProductPurchaseHistoryDTO> getProductPurchaceHistory(String productName);
}
