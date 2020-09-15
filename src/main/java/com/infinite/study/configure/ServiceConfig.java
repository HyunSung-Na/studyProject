package com.infinite.study.configure;

import com.zaxxer.hikari.HikariDataSource;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class ServiceConfig {

    @Bean
    @Profile("test")
    public DataSource testDataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        HikariDataSource dataSource = (HikariDataSource) factory.build();
        dataSource.setPoolName("test");
        dataSource.setMinimumIdle(1);
        dataSource.setMaximumPoolSize(1);
        return new Log4jdbcProxyDataSource(dataSource);
    }
}
