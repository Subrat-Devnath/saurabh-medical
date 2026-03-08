package com.product.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.product.mgmt", "com.user.mgmt", "com.security.config"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.user.mgmt.client")
@EnableCassandraRepositories(basePackages = "com.product.mgmt.repository.dao")
@EnableJpaRepositories(basePackages = "com.user.mgmt.repository.dao")
@EntityScan(basePackages = "com.user.mgmt.repository.entity")
public class ProductMgmtRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMgmtRestApplication.class, args);
    }

}
