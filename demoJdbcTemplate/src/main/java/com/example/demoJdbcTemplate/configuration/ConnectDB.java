package com.example.demoJdbcTemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.security.spec.NamedParameterSpec;

@Configuration
public class ConnectDB {

    @Bean(name = "ConnectMySql")
    public DataSource myDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_database");
        dataSource.setUsername("root");
        dataSource.setPassword("quangthai3110");
        return dataSource;
    }

    @Bean("CustomJdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(myDataSource());
    }

    @Bean("CustomNameJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(myDataSource());
    }
}
