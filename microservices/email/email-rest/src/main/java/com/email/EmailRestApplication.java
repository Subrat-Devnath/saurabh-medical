package com.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.email", "com.security.config", "com.user.mgmt.repository"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.product.mgmt.client")
@EntityScan(basePackages = {"com.email.repository.entity", "com.user.mgmt.repository.entity"})
@EnableJpaRepositories(basePackages = {"com.email.repository.dao", "com.user.mgmt.repository.dao"})
public class EmailRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailRestApplication.class, args);
    }

}
