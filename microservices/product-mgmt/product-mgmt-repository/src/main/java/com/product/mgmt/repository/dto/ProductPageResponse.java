package com.product.mgmt.repository.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductPageResponse {

    private List<ProductDTO> products;

    // send this in next API request
    private String nextPageState;

    private boolean hasNext;
}