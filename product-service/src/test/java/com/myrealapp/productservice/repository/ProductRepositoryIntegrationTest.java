package com.myrealapp.productservice.repository;

import com.myrealapp.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Testcontainers
public class ProductRepositoryIntegrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndRetrieveProduct() {
        // Arrange
        Product product = new Product("Test Product", "Test Description", new BigDecimal("99.99"));

        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        assertNotNull(savedProduct.getId());
        
        Product retrievedProduct = productRepository.findById(savedProduct.getId()).orElse(null);
        assertNotNull(retrievedProduct);
        assertEquals(product.getName(), retrievedProduct.getName());
        assertEquals(product.getDescription(), retrievedProduct.getDescription());
        assertEquals(0, product.getPrice().compareTo(retrievedProduct.getPrice()));
    }
}