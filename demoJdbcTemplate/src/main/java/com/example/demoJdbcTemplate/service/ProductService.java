package com.example.demoJdbcTemplate.service;
import com.example.demoJdbcTemplate.Entity.Product;
import com.example.demoJdbcTemplate.repository.ProductDAO;
import com.example.demoJdbcTemplate.repository.ProductRepository;
import com.example.demoJdbcTemplate.specification.SpecificationProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.demoJdbcTemplate.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements Services{

    @Autowired
//    @Qualifier("JdbcTemplate")
    @Qualifier("NamedJdbcTemplate")
    private ProductDAO productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<ProductDTO> dto = new ArrayList<ProductDTO>();
        
        return productRepository.getAllProduct();

    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.addProduct(productDTO);
    }

    @Override
    public void updateProduct(Long id, ProductDTO updatedProductDTO) {
        Product product = convertToEntityId(id, updatedProductDTO);
        productRepository.updateProduct(id,updatedProductDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setColor(productDTO.getColor());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    @Override
    public Product convertToEntityId(Long id, ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setColor(productDTO.getColor());
        product.setSize(productDTO.getSize());
        product.setPrice(productDTO.getPrice());
        return product;
    }
    @Override
    public  List<Product> findProductbyName(String name){

        return productRepository.findProductbyName(name);
    }
}
