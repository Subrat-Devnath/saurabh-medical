package com.product.mgmt.repository.impl;

import com.common.service.configuration.ObjectBuilder;
import com.common.service.dtos.PaginationCriteria;
import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dao.ProductDAO;
import com.product.mgmt.repository.dao.ProductPurchaceHistoryDAO;
import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;
import com.product.mgmt.repository.entity.ProductPurchaceHistoryEntity;
import com.product.mgmt.repository.entity.ProductPurchaceHistoryEntityId;
import com.security.config.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryServiceImpl implements ProductRepository {

    @Autowired
    private ProductDAO productDao;

    @Autowired
    private ProductPurchaceHistoryDAO productPurchaceHistoryDAO;

    @Override
    public void addProduct(ProductDTO productDto) {

        //Set current date as purchase date for the product
        productDto.setPurchasedate(java.time.LocalDate.now()
                .atStartOfDay(java.time.ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli());

        ProductEntity entity = ObjectBuilder.buildDtoFromEntity(productDto, null, ProductEntity.class);
        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrganzationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productDto.getProductName().toUpperCase());
        entity.setProductEntityId(productEntityId);
        productDao.save(entity);

        ProductPurchaceHistoryEntityId productPurchaceHistoryEntityId = new ProductPurchaceHistoryEntityId();
        productPurchaceHistoryEntityId.setOrganizationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productPurchaceHistoryEntityId.setProductName(productDto.getProductName().toUpperCase());
        productPurchaceHistoryEntityId.setPurchaseDate(productDto.getPurchasedate());

        ProductPurchaceHistoryEntity productPurchaceHistoryEntity = ObjectBuilder.buildDtoFromEntity(productDto, null, ProductPurchaceHistoryEntity.class);

        productPurchaceHistoryEntity.setProductPurchaceHistoryEntityId(productPurchaceHistoryEntityId);


        productPurchaceHistoryDAO.save(productPurchaceHistoryEntity);
    }

    @Override
    public ProductDTO getProduct(String productName) {

        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrganzationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productName.toUpperCase());

        Optional<ProductEntity> entityOpt = productDao.findById(productEntityId);

        if (entityOpt.isEmpty()) {
            return null;
        }

        return entityOpt.filter(entity -> !entity.isDeleted()).map(productEntity -> ObjectBuilder.buildDtoFromEntity(productEntity, productEntity.getProductEntityId(), ProductDTO.class)).orElse(null);
    }

    @Override
    public List<ProductDTO> searchProduct(String productName) {

        if (!StringUtils.hasLength(productName)) {
            return List.of();
        }
        String start = productName.toUpperCase();
        String end = productName.toUpperCase() + "~";

        List<ProductEntity> products = productDao.searchProducts(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId(), start, end);

        if (CollectionUtils.isEmpty(products)) {
            return List.of();
        }

        return products.stream().filter(entity -> !entity.isDeleted()).map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductEntityId(), ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productName) {
        ProductEntityId productEntityId = new ProductEntityId();
        productEntityId.setOrganzationId(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId());
        productEntityId.setProductName(productName.toUpperCase());

        productDao.deleteById(productEntityId);
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        List<ProductEntity> allProducts = productDao.findAll();

        if (CollectionUtils.isEmpty(allProducts)) {
            return null;
        }

        return allProducts.stream().filter(entity -> !entity.isDeleted()).map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductEntityId(), ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsWithPagination(PaginationCriteria paginationCriteria) {

        if (Objects.isNull(paginationCriteria)) {
            return List.of();
        }

        List<ProductEntity> products = productDao.getProductsWithPagination(Objects.requireNonNull(SecurityUtil.getPrincipal()).getOrgId(), PageRequest.of(paginationCriteria.getStartIndex().intValue(), paginationCriteria.getPageSize().intValue()));

        if (CollectionUtils.isEmpty(products)) {
            return List.of();
        }

        return products.stream().filter(entity -> !entity.isDeleted()).map(entity -> ObjectBuilder.buildDtoFromEntity(entity, entity.getProductEntityId(), ProductDTO.class)).collect(Collectors.toList());
    }

}
