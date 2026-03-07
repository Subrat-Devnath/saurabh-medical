package com.product.mgmt.controller;

import java.util.List;

import com.common.service.dtos.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.mgmt.repository.dto.ProductDto;
import com.product.mgmt.service.ProductService;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping(path = "/product/{productName}")
    public ProductDto getProduct(@PathVariable(name = "productName") String productName) {
        return productService.getProduct(productName);
    }

    @GetMapping(path = "/{productName}")
    public void deleteProduct(@PathVariable(name = "productName") String productName) {
        productService.deleteProduct(productName);
    }

    @GetMapping(path = "/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/products-with-pagination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProductsWithPagination(@RequestBody Pageable pageable) {
        return productService.getProductsWithPagination(pageable);
    }


}
