package com.hms.mangement.UserManagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@org.springframework.context.annotation.Configuration

public class Configuration {
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriverName;
    @Value("${url}")
    private String jdbcURl;

    @Value("${spring.datasource.url}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource datasource() throws PropertyVetoException {
        /*ApplicationContext context = new ClassPathXmlApplicationContext("been.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        return dataSource;*/
        System.out.println("hello"+jdbcURl);
        return DataSourceBuilder
                .create()
                .username("root")
                .password("beyondm")
                .url("jdbc:mysql://localhost:3306/userDatabase")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        try {
            return new JdbcTemplate(datasource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public UserDAOClass userDAO(){
        UserDAOClass userDAOClass = new UserDAOClass();
        userDAOClass.setJdbcTemplate(jdbcTemplate());
        return userDAOClass;
    }



}