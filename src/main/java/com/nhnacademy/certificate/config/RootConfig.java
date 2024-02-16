package com.nhnacademy.certificate.config;

import com.nhnacademy.certificate.Base;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(
        basePackageClasses = Base.class,
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
@PropertySource("classpath:/db.properties")
public class RootConfig {
    @Bean
    public DataSource dataSource(Environment environment) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));

        int size = Integer.parseInt(Objects.requireNonNull(environment.getProperty("db.size")));
        dataSource.setInitialSize(size);
        dataSource.setMaxTotal(size);
        dataSource.setMinIdle(size);
        dataSource.setMaxIdle(size);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }
}
