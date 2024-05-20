package com.example.demoJdbcTemplate.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Noiban> product = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }
}

