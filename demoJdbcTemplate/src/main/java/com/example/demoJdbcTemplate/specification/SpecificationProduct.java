package com.example.demoJdbcTemplate.specification;
import com.example.demoJdbcTemplate.Entity.Product;
import org.springframework.data.jpa.domain.Specification;


public class SpecificationProduct {
    public static Specification<Product> hasProduct(String nameProduct){
        return(root, query,criteriaBuilder) ->
            criteriaBuilder.like(root.<String>get("name"),"%" + nameProduct + "%");
    }
}