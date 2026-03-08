package com.product.mgmt.controller;


import com.product.mgmt.repository.dto.ProductPurchaseHistoryDTO;
import com.product.mgmt.service.ProductPurchaceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductPurchaceHistoryController {

    @Autowired
    private ProductPurchaceHistoryService productPurchaceHistoryService;

    @GetMapping(path = "/prices/{productName}")
    public List<ProductPurchaseHistoryDTO> getProductPurchaceHistory(@PathVariable(name = "productName") String productName) {
        return productPurchaceHistoryService.getProductPurchaceHistory(productName);
    }

}
