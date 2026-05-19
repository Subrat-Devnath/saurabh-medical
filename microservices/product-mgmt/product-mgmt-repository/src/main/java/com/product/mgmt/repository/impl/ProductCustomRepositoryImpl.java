package com.product.mgmt.repository.impl;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.product.mgmt.repository.ProductCustomRepository;
import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.repository.dto.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final CqlSession session;

    @Override
    public ProductPageResponse getProducts(
            String organizationId,
            Integer pageSize,
            String pageState
    ) {

        SimpleStatementBuilder statementBuilder =
                SimpleStatement.builder(
                                "SELECT * FROM product WHERE organization_id = ?")
                        .addPositionalValue(organizationId)
                        .setPageSize(pageSize);

        // set paging state if available
        if (pageState != null && !pageState.isBlank()) {

            ByteBuffer pagingState = ByteBuffer.wrap(
                    Base64.getDecoder().decode(pageState)
            );

            statementBuilder.setPagingState(pagingState);
        }

        ResultSet resultSet = session.execute(statementBuilder.build());

        List<ProductDTO> products = new ArrayList<>();

        for (Row row : resultSet) {

            ProductDTO product = new ProductDTO();

            product.setProductName(row.getString("product_name"));

            product.setCategory(row.getString("category"));
            product.setDeleted(row.getBoolean("is_deleted"));
            product.setExpiryDate(row.getLong("expiry_date"));
            product.setExpired(row.getBoolean("is_expired"));
            product.setCreatedDate(row.getLong("created_date"));
            product.setCreatedUserId(row.getString("created_user_id"));
            product.setCreatedUserName(row.getString("created_user_name"));
            product.setUpdatedDate(row.getLong("updated_date"));
            product.setUpdatedUserId(row.getString("updated_user_id"));
            product.setUpdatedUserName(row.getString("updated_user_name"));

            products.add(product);
        }

        ProductPageResponse response = new ProductPageResponse();
        response.setProducts(products);

        PagingState nextPagingState =
                resultSet.getExecutionInfo().getSafePagingState();

        if (nextPagingState != null) {

            response.setNextPageState(
                    Base64.getEncoder()
                            .encodeToString(nextPagingState.toBytes())
            );

            response.setHasNext(true);

        } else {

            response.setHasNext(false);
        }

        return response;
    }
}