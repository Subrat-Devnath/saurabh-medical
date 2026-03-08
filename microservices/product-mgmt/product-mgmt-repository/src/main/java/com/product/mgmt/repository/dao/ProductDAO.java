package com.product.mgmt.repository.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;
import org.springframework.data.domain.Pageable;

public interface ProductDAO extends CassandraRepository<ProductEntity, ProductEntityId> {

    @Query("select * from product where organization_id = ?0 and product_name = ?1")
    ProductEntity getProduct(String organizationID, String productName);

    @Query("select * from product where organization_id = ?0")
    List<ProductEntity> getProductsWithPagination(String organizationID, Pageable pageable);

    @Query("SELECT * FROM product WHERE organization_id = ?0 AND product_name >= ?1 AND product_name < ?2")
    List<ProductEntity> searchProducts(String organizationId, String start, String end);
}
