package com.product.mgmt.repository.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;

public interface ProductDao extends CassandraRepository<ProductEntity, ProductEntityId> {

}
