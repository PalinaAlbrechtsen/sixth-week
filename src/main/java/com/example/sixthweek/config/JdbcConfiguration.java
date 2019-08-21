package com.example.sixthweek.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.sixthweek")
public class JdbcConfiguration {

    @Bean
    public DataSource postgreSQL() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/library")
                .username("postgres")
                .password("pass")
                .build();
    }
}
