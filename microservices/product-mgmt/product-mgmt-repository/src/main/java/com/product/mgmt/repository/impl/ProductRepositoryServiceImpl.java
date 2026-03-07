package com.product.mgmt.repository.impl;

import com.common.service.configuration.ObjectBuilder;
import com.common.service.dtos.Pageable;
import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dao.ProductDao;
import com.product.mgmt.repository.dto.ProductDto;
import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;

import com.security.config.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryServiceImpl implements ProductRepository {

    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(ProductDto productDto) {
        ProductEntity entity = ObjectBuilder.buildDtoFromEntity(productDto, null, ProductEntity.class);
        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrgansationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productDto.getProductName().toUpperCase());
        entity.setProductEntityId(productEntityId);
        System.out.println("Product for save: " + entity);
        productDao.save(entity);
    }

    @Override
    public ProductDto getProduct(String productName) {

        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrgansationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productName.toUpperCase());

        Optional<ProductEntity> entityOpt = productDao.findById(productEntityId);

        return entityOpt.map(productEntity -> ObjectBuilder.buildDtoFromEntity(productEntity, null, ProductDto.class)).orElse(null);

    }

    @Override
    public void deleteProduct(String productName) {
        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrgansationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productName.toUpperCase());

        productDao.deleteById(productEntityId);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<ProductEntity> allProducts = productDao.findAll();

        if (CollectionUtils.isEmpty(allProducts)) {
            return null;
        }

        return allProducts.stream().map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductEntityId(), ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsWithPagination(Pageable pageable) {

        if (Objects.isNull(pageable)) {
            return List.of();
        }

        List<ProductEntity> products = productDao.getProductsWithPagination(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId(), PageRequest.of(pageable.getStartIndex().intValue(), pageable.getPageSize().intValue()));

        if (CollectionUtils.isEmpty(products)) {
            return List.of();
        }

        return products.stream().map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductEntityId(), ProductDto.class))
                .collect(Collectors.toList());
    }

}
