package com.product.mgmt.repository.impl;

import com.common.service.configuration.ObjectBuilder;
import com.product.mgmt.repository.ProductPurchaceHistoryRepository;
import com.product.mgmt.repository.dao.ProductPurchaceHistoryDAO;
import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;
import com.product.mgmt.repository.entity.ProductPurchaceHistoryEntity;
import com.security.config.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ProductPurchaceHistoryRepositoryImpl implements ProductPurchaceHistoryRepository {

    @Autowired
    private ProductPurchaceHistoryDAO productPurchaceHistoryDAO;

    @Override
    public List<ProductPurchaseHistoryDTO> getProductPurchaceHistory(String productName) {

        if (!StringUtils.hasLength(productName)) {
            return List.of();
        }

        List<ProductPurchaceHistoryEntity> productPrices = productPurchaceHistoryDAO.getProductPrices(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId(), productName.toUpperCase());

        if (CollectionUtils.isEmpty(productPrices)) {
            return List.of();
        }

        return productPrices.stream().filter(entity -> !entity.isDeleted()).map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductPurchaceHistoryEntityId(), ProductPurchaseHistoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductPurchaseHistoryDTO> getProductQuantities(List<String> productNames) {
        List<ProductPurchaceHistoryEntity> productPrices = productPurchaceHistoryDAO.getProductQuantities(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId(), productNames);

        if (CollectionUtils.isEmpty(productPrices)) {
            return List.of();
        }

        return productPrices.stream().filter(entity -> !entity.isDeleted()).map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductPurchaceHistoryEntityId(), ProductPurchaseHistoryDTO.class)).collect(Collectors.toList());
    }
}
