package com.example.demoJdbcTemplate.service;

import com.example.demoJdbcTemplate.Entity.Product;
import com.example.demoJdbcTemplate.dto.ProductDTO;

import java.util.List;

@org.springframework.stereotype.Service
public interface Services {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(ProductDTO productDTO);
    void updateProduct(Long id, ProductDTO updatedProductDTO);
    void deleteProduct(Long id);
    Product convertToEntity(ProductDTO productDTO);
    Product convertToEntityId(Long id, ProductDTO productDTO);
    List<Product> findProductbyName(String name);
}
