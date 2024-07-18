/*package com.example.StudentRegistryBackend.config;

import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.example.StudentRegistryBackend.repository.primary"}
)
public class PrimaryDatabaseConfig {

    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder primaryEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), null, null);
    }

    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryEntityManagerFactoryBuilder") EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.example.StudentRegistryBackend.model.primary")
                .persistenceUnit("primary")
                .build();
    }
    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}*/