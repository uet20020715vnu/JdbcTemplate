package com.example.demoJdbcTemplate.repository;

import com.example.demoJdbcTemplate.Entity.Product;
import com.example.demoJdbcTemplate.dto.ProductDTO;
import com.example.demoJdbcTemplate.rowmappers.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("JdbcTemplate")
public class ProductJdbcTemplate implements ProductDAO{

    @Autowired
    @Qualifier("CustomJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    private ProductJdbcTemplate(@Qualifier("ConnectMySql") DataSource dataSource){
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        String sql = "INSERT INTO product(name, color, size, price)" +
                " VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, productDTO.getName(), productDTO.getColor(), productDTO.getSize(), productDTO.getPrice());
    }

    @Override
    public void updateProduct(long id, ProductDTO productDTO) {
        String sql = "UPDATE product SET name = ?, color = ?, size = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, productDTO.getName(), productDTO.getColor(), productDTO.getSize(), productDTO.getPrice(), id);
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product getProductById(long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> products= jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, id);
            }
        }, new ProductRowMapper());
        return products.isEmpty() ? null : products.get(0);
    }

    @Override
    public List<Product> findProductbyName(String name) {
        String sql = "SELECT * FROM product WHERE name = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
            }
        }, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public void deleteProduct(long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, id);
            }
        });
    }
}
