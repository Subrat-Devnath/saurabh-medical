package com.product.mgmt.controller;

import com.common.service.dtos.PaginationCriteria;
import com.product.mgmt.repository.dto.ProductDTO;
import com.product.mgmt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody ProductDTO productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping(path = "/product/{productName}")
    public ProductDTO getProduct(@PathVariable(name = "productName") String productName) {
        return productService.getProduct(productName);
    }

    @GetMapping(path = "/products/{productName}")
    public List<ProductDTO> searchProduct(@PathVariable(name = "productName") String productName) {
        return productService.searchProduct(productName);
    }

    @GetMapping(path = "/{productName}")
    public void deleteProduct(@PathVariable(name = "productName") String productName) {
        productService.deleteProduct(productName);
    }

    @GetMapping(path = "/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/products-with-pagination", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProductsWithPagination(@RequestBody PaginationCriteria paginationCriteria) {
        return productService.getProductsWithPagination(paginationCriteria);
    }


}
