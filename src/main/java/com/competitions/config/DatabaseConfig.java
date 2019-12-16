package com.competitions.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@ComponentScan(basePackages = {"com.competitions.entities", "com.competitions.services"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.competitions.repos")
@PropertySource("classpath:./application.properties")
public class DatabaseConfig {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean(value = "myDataSource")
    public DataSource dataSource() {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
            dataSource.setUrl(env.getProperty("spring.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.datasource.password"));
            return dataSource;
        } catch (Exception e) {
            logger.error("Populator DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean(value = "h2DataSource")
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(H2).build();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", env.getProperty("spring.hibernate.dialect"));
        hibernateProp.put("hibernate.hbm2ddl.auto", env.getProperty("spring.hibernate.hbm2ddl.auto"));
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", Integer.parseInt(env.getProperty("spring.hibernate.max_fetch_depth")));
        hibernateProp.put("hibernate.jdbc.batch_size", Integer.parseInt(env.getProperty("spring.hibernate.jdbc.batch_size")));
        hibernateProp.put("hibernate.jdbc.fetch_size", Integer.parseInt(env.getProperty("spring.hibernate.jdbc.fetch_size")));
        return hibernateProp;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.competitions.entities");
        em.setDataSource(h2DataSource());
        em.setJpaProperties(hibernateProperties());
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.afterPropertiesSet();
        return em.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}