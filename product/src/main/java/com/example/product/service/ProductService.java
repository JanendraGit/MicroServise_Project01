package com.example.product.service;

import com.example.product.controller.request.ProductRequest;
import com.example.product.controller.response.ProductResponse;
import com.example.product.domain.Product;

import java.util.List;

public interface ProductService {
    void addProduct(ProductRequest  productRequest);
    void updateProduct(Long id,ProductRequest  productRequest);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> findAllProducts();
}
