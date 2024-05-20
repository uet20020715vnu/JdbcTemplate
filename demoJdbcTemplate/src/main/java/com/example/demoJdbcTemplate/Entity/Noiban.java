package com.example.demoJdbcTemplate.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "noiban")
@Data
@Entity

public class Noiban{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sources")
    private String sources;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
