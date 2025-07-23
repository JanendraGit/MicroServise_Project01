package com.example.product.service.impl;

import com.example.product.controller.request.ProductRequest;
import com.example.product.controller.response.ProductResponse;
import com.example.product.domain.Product;
import com.example.product.exception.ProductException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequest productRequest) {
        Optional<Product>  optionalProduct = productRepository.findByName(productRequest.getName());
        if (optionalProduct.isPresent()) {
            throw new ProductException("product not found");
        }
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new ProductException("product not found"));
        product1.setName(productRequest.getName());
        product1.setPrice(productRequest.getPrice());
        productRepository.save(product1);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductException("product not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new ProductException("product not found"));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = productList.stream().map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build())
                        .collect(Collectors.toList());
        return productResponseList;
    }
}
