package com.product.mgmt.service.impl;

import com.common.service.dtos.PaginationCriteria;
import com.product.mgmt.repository.ProductPurchaceHistoryRepository;
import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;
import com.product.mgmt.service.ProductService;
import com.security.config.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPurchaceHistoryRepository productPurchaceHistoryRepository;

    @Override
    public void addProduct(ProductDTO productDto) {
        productRepository.addProduct(productDto);
    }

    @Override
    public ProductDTO getProduct(String productName) {

        ProductDTO product = productRepository.getProduct(productName);

        if (product == null) {
            return null;
        }


        Map<String, Long> mapOfProductKeysAndTotalQuantity = mapOfProductKeysAndTotalQuantity(Collections.singletonList(product.getProductName()));

        product.setTotalQuantity(mapOfProductKeysAndTotalQuantity.get(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId() + "-" + product.getProductName()));

        return product;
    }

    private Map<String, Long> mapOfProductKeysAndTotalQuantity(List<String> productNames) {

        List<ProductPurchaseHistoryDTO> productQuantities = productPurchaceHistoryRepository.getProductQuantities(productNames);

        if (CollectionUtils.isEmpty(productQuantities)) {
            return Collections.emptyMap();
        }

        return productQuantities.stream()
                .filter(p -> p.getRemainingQuantity() != null)
                .collect(Collectors.groupingBy(
                        p -> Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId() + "-" + p.getProductName(),
                        Collectors.summingLong(ProductPurchaseHistoryDTO::getRemainingQuantity)
                ));
    }

    @Override
    public List<ProductDTO> searchProduct(String productName) {

        List<ProductDTO> productDTOS = productRepository.searchProduct(productName);

        if (CollectionUtils.isEmpty(productDTOS)) {
            return List.of();
        }

        Map<String, Long> mapOfProductKeysAndTotalQuantity = mapOfProductKeysAndTotalQuantity(productDTOS.stream().map(ProductDTO::getProductName).toList());

        productDTOS.stream().forEach(productDTO -> productDTO.setTotalQuantity(mapOfProductKeysAndTotalQuantity.get(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId() + "-" + productDTO.getProductName())));

        return productDTOS;
    }

    @Override
    public void deleteProduct(String productName) {
        productRepository.deleteProduct(productName);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsWithPagination(PaginationCriteria paginationCriteria) {
        return productRepository.getProductsWithPagination(paginationCriteria);
    }


}
