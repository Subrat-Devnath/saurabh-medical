package com.product.mgmt.repository.dao;

import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductPurchaceHistoryEntity;
import com.product.mgmt.repository.entity.ProductPurchaceHistoryEntityId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

public interface ProductPurchaceHistoryDAO extends CassandraRepository<ProductPurchaceHistoryEntity, ProductPurchaceHistoryEntityId> {

    @Query("select * from product_purchace_history where organization_id = ?0 and product_name = ?1")
    List<ProductPurchaceHistoryEntity> getProductPrices(String organizationID, String productName);

    @Query("select organization_id, product_name, purchased_quantity, remaining_quantity, sold_quantity, is_deleted from product_purchace_history where organization_id = ?0 and product_name in ?1")
    List<ProductPurchaceHistoryEntity> getProductQuantities(String organizationID, List<String> productNames);
}
