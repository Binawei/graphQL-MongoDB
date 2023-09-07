package com.eazipay.eazipayuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EazipayuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazipayuserApplication.class, args);
    }

}
