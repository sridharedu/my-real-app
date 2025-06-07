package com.myrealapp.productservice.service;

import com.myrealapp.productservice.model.Product;
import com.myrealapp.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
        testProduct = new Product("Test Product", "Test Description", new BigDecimal("99.99"));
        testProduct.setId("1");
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(testProduct);
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts, actualProducts);
        verify(productRepository).findAll();
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenProductExists() {
        // Arrange
        when(productRepository.findById("1")).thenReturn(Optional.of(testProduct));

        // Act
        Optional<Product> result = productService.getProductById("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testProduct, result.get());
        verify(productRepository).findById("1");
    }

    @Test
    void createProduct_ShouldReturnSavedProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // Act
        Product result = productService.createProduct(testProduct);

        // Assert
        assertEquals(testProduct, result);
        verify(productRepository).save(testProduct);
    }

    @Test
    void deleteProduct_ShouldCallRepositoryDelete() {
        // Act
        productService.deleteProduct("1");

        // Assert
        verify(productRepository).deleteById("1");
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // Act
        Product result = productService.updateProduct(testProduct);

        // Assert
        assertEquals(testProduct, result);
        verify(productRepository).save(testProduct);
    }
}