package com.myrealapp.productservice.repository;

import com.myrealapp.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Basic CRUD operations are automatically implemented by Spring Data MongoDB
}