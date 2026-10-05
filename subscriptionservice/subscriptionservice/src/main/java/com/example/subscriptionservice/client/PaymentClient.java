package com.example.subscriptionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "paymentservice")
public interface PaymentClient {

    @PostMapping("/payments/authorize")
    String authorizePayment(
            @RequestParam("userId") Long userId,
            @RequestParam("amount") Double amount
    );
}