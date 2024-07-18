/*package com.example.StudentRegistryBackend.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/userRegistry");
        dataSource.setUsername("lisalin");
        dataSource.setPassword("lys220130");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/studentRegistry");
        dataSource.setUsername("lisalin");
        dataSource.setPassword("lys220130");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}
*/