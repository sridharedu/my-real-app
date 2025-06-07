package com.myrealapp.productservice.service;

import com.myrealapp.productservice.model.Product;
import com.myrealapp.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}