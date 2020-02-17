package com.bangla.snacks.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.bangla.snacks")
@EntityScan(basePackages = "com.bangla.snacks.customer.db.models")
@EnableJpaRepositories(basePackages = "com.bangla.snacks.customer.repository")
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
