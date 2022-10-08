package com.example.project.multipledatasource.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "employeeEntityManagerFactoryBean",
        transactionManagerRef = "employeeTransactionManager",
        basePackages = {"com.example.project.multipledatasource.repository.postgres"}
)
public class PostgresConfig {

    @Bean(name = "test1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource test1Datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "employeeEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean
    employeeEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                             @Qualifier("test1DataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.example.project.multipledatasource.entity")
                .persistenceUnit("EMPLOYEE")
                .build();
    }

    @Bean(name = "employeeTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("employeeEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
