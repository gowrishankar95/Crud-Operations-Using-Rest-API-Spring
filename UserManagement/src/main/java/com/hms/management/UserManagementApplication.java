package com.hms.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hms.management","com.hms.management.dao", "com.hms.management.rest","com.hms.management.dao.impl"})
public class UserManagementApplication {
    public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);

	}

}
