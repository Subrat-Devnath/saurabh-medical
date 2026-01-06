package com.user.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.product.client")
public class UserMgmtRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgmtRestApplication.class, args);
	}

}
