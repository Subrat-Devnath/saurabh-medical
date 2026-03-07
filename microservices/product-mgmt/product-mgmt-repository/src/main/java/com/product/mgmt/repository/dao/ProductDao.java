package com.product.mgmt.repository.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;
import org.springframework.data.domain.Pageable;

public interface ProductDao extends CassandraRepository<ProductEntity, ProductEntityId> {

    @Query("select * from product where organisation_id = ?0 and product_name = ?1")
    ProductEntity getProduct(String organisationID, String productName);

    @Query("select * from product where organisation_id = ?0")
    List<ProductEntity> getProductsWithPagination(String organisationID, Pageable pageable);
}
