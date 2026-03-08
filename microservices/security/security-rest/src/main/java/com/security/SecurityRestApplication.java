package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.user.mgmt.repository.dao")
@EntityScan(basePackages = "com.user.mgmt.repository.entity")
public class SecurityRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityRestApplication.class, args);
	}

}
