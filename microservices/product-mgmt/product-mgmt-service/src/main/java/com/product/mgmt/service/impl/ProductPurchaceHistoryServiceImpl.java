package com.product.mgmt.service.impl;

import com.product.mgmt.repository.ProductPurchaceHistoryRepository;
import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;
import com.product.mgmt.service.ProductPurchaceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPurchaceHistoryServiceImpl implements ProductPurchaceHistoryService {

    @Autowired
    private ProductPurchaceHistoryRepository productPurchaceHistoryRepository;

    @Override
    public List<ProductPurchaseHistoryDTO> getProductPurchaceHistory(String productName) {
        return productPurchaceHistoryRepository.getProductPurchaceHistory(productName);
    }
}
