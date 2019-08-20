package com.mschoeffel.mydms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
//@EnableWebMvc
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean(name = "securityDataSource")
    public DataSource securityDataSource() {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));
        return driverManagerDataSource;
    }

}
