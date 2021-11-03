package it1.doan.webapp.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration

public class ConnectSQLService {

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder =   DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=WEBBANGIAY");
        dataSourceBuilder.username("trung");
        dataSourceBuilder.password("trung");
        return dataSourceBuilder.build();
    }

}
