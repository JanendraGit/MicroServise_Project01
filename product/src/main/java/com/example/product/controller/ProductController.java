package com.example.product.controller;

import com.example.product.controller.request.ProductRequest;
import com.example.product.controller.response.ProductListResponse;
import com.example.product.controller.response.ProductResponse;
import com.example.product.exception.ProductException;
import com.example.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping(value = "/product")
    public void addProduct(@RequestBody ProductRequest productRequest) throws ProductException {
        productService.addProduct(productRequest);
    }
    @GetMapping(value = "/product/{product_id}")
    public ProductResponse getProductById(@PathVariable Long product_id) throws ProductException {
        return productService.getProductById(product_id);
    }
    @PutMapping(value = "/product/{product_id}")
    public void updateProduct(@PathVariable Long product_id, @RequestBody ProductRequest productRequest) throws ProductException {
        productService.updateProduct(product_id,productRequest);
    }
    @DeleteMapping(value = "/{product_id}")
    public void deleteProduct(@PathVariable Long product_id) throws ProductException {
        productService.deleteProduct(product_id);
    }
    @GetMapping(value = "/product")
    public ProductListResponse findAllProducts(){
        List<ProductResponse> productResponses = productService.findAllProducts();

        var Product = productResponses.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build()).toList();
        return ProductListResponse.builder()
                .products(Product)
                .build();
    }
}
