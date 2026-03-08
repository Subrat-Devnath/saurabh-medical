package com.product.mgmt.repository.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@Data
@PrimaryKeyClass
public class ProductPurchaceHistoryEntityId implements Serializable {

    @PrimaryKeyColumn(name = "organization_id", type = PrimaryKeyType.PARTITIONED)
    private String organizationId;

    @PrimaryKeyColumn(name = "product_name", type = PrimaryKeyType.PARTITIONED)
    private String productName;

    @PrimaryKeyColumn(name = "purchase_date", ordinal = 0, ordering = Ordering.DESCENDING, type = PrimaryKeyType.CLUSTERED)
    private Long purchaseDate;
}

