package com.hms.mangement.UserManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:application.properties")
public class Configurations {

    @Value("${spring.datasource.driver-class-name}")
    private  String jdbcDriverName;

    @Value("${spring.datasource.url}")
    private String jdbcURl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource datasource() throws PropertyVetoException {

        return DataSourceBuilder
                .create()
                .username(dbUsername)
                .password(dbPassword)
                .url(jdbcURl)
                .driverClassName(jdbcDriverName)
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