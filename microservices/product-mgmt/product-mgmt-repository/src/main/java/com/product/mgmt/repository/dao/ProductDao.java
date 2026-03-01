package com.product.mgmt.repository.dao;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;

public interface ProductDao extends CassandraRepository<ProductEntity, ProductEntityId> {

	@Query("select * from product where organisation_id = ?0 and product_name = ?1")
	ProductEntity getProduct(UUID organisationID, String productName);
}
