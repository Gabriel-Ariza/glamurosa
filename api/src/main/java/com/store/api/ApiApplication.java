package com.store.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.store.api.repository")
@ComponentScan(basePackages = {"com.store.api"})
public class ApiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
