package com.hms.management;
import com.hms.management.dao.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
//@PropertySource("classpath")
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
        System.out.println("inside data source bean");
        return DataSourceBuilder
                .create()
                .username(dbUsername)
                .password(dbPassword)
                .url(jdbcURl)
                .driverClassName(jdbcDriverName)
                .build();


        /*ApplicationContext context = new ClassPathXmlApplicationContext("been.xml");
        DataSource dataSource= (DataSource) context.getBean("dataSource");
        return dataSource;
        */
    }

    @Bean("jdbcTemplateBean")
    public JdbcTemplate jdbcTemplate(){
        System.out.println("inside jdbcTemplate  bean");

        try {
            return new JdbcTemplate(datasource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }

    }
    @Bean(name = "userRepositoryImplBean")
    public UserRepositoryImpl userDAO() {
        System.out.println("inside user Repository  bean");
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return userRepository;
    }


}