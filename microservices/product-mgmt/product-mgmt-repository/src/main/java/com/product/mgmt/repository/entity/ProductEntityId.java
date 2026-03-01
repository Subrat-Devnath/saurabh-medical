package com.product.mgmt.repository.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.Data;

@Data
@PrimaryKeyClass
public class ProductEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "organizat_id", type = PrimaryKeyType.PARTITIONED)
	private UUID organsationId;

	@PrimaryKeyColumn(name = "product_name", type = PrimaryKeyType.PARTITIONED)
	private String productName;

}
