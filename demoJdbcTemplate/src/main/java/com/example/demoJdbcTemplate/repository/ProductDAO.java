package com.example.demoJdbcTemplate.repository;

import com.example.demoJdbcTemplate.Entity.Product;
import com.example.demoJdbcTemplate.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {
    void addProduct(ProductDTO productDTO);
    void updateProduct(long id, ProductDTO productDTO);
    List<Product> getAllProduct();
    Product getProductById(long id);
    List<Product> findProductbyName(String name);
    void deleteProduct(long id);
}
