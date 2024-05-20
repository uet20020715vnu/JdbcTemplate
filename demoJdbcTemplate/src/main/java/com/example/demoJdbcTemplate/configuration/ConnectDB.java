package com.example.demoJdbcTemplate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConnectDB {

    @Bean(name = "ConnectMySql")
    public DataSource myDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/thread");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
