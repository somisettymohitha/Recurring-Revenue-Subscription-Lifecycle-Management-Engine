package com.example.subscriptionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubscriptionserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                SubscriptionserviceApplication.class,
                args
        );
    }
}

