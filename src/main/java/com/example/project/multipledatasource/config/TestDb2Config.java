package com.example.project.multipledatasource.config;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "personEntityManagerFactoryBean",
        transactionManagerRef = "personTransactionManager",
        basePackages = {"com.example.project.multipledatasource.repository.person"}
)
public class TestDb2Config {

    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource test2Datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "personEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean
    personEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                             @Qualifier("test2DataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.example.project.multipledatasource.entity")
                .persistenceUnit("PERSON")
                .build();
    }

    @Bean(name = "personTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("personEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}