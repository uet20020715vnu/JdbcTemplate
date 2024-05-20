package com.example.demoJdbcTemplate.repository;

import com.example.demoJdbcTemplate.Entity.Product;
import com.example.demoJdbcTemplate.dto.ProductDTO;
import com.example.demoJdbcTemplate.rowmappers.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("NamedJdbcTemplate")
public class ProductNamedJdbcTemplate implements ProductDAO{

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public ProductNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate){
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        String sql = "INSERT INTO product(name, color, size, price)" +
                " VALUES (:city, :phone)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", productDTO.getName())
                .addValue("color", productDTO.getColor())
                .addValue("size", productDTO.getSize())
                .addValue("price", productDTO.getPrice());
        namedJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public void updateProduct(long id, ProductDTO productDTO) {
        String sql = "UPDATE product SET name = :name,color = :color,size = :size, price = :price WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id)
                .addValue("name", productDTO.getName())
                .addValue("color", productDTO.getColor())
                .addValue("size", productDTO.getSize())
                .addValue("price", productDTO.getPrice());
        namedJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM product";
        return namedJdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product getProductById(long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        List<Product> products = namedJdbcTemplate.query(sql, parameterSource, new ProductRowMapper());
        return products.get(0);
    }

    @Override
    public List<Product> findProductbyName(String name) {
        String sql = "SELECT * FROM product WHERE name = :name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return namedJdbcTemplate.query(sql, parameterSource, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public void deleteProduct(long id) {
        String sql = "DELETE FROM product WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        namedJdbcTemplate.update(sql, parameterSource);
    }
}
