package com.bangla.snacks.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.bangla.snacks",
		"com.bangla.snacks.common"})
@EntityScan(basePackages = {
		"com.bangla.snacks.customer.db.models",
		"com.bangla.snacks.common.change.log"})
@EnableJpaRepositories(basePackages = {
		"com.bangla.snacks.customer.repository",
		"com.bangla.snacks.common.change.log"
})
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
