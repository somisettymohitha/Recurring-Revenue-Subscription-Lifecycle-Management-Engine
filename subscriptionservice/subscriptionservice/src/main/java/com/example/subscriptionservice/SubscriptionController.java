package com.example.subscriptionservice;


import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscriptionservice.client.PaymentClient;
import com.example.subscriptionservice.repository.SubscriptionRepository;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;
    private final PaymentClient paymentClient;

    public SubscriptionController(
            SubscriptionRepository subscriptionRepository,
            PaymentClient paymentClient) {

        this.subscriptionRepository = subscriptionRepository;
        this.paymentClient = paymentClient;
    }

    @PostMapping
    public Subscription createSubscription(
            @RequestBody Subscription subscription) {

        if (subscription.getStartDate() == null) {
            subscription.setStartDate(LocalDate.now());
        }

        if (subscription.getStatus() == null) {
            subscription.setStatus(SubscriptionStatus.ACTIVE);
        }

        return subscriptionRepository.save(subscription);
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getByUserId(
            @PathVariable Long userId) {

        return subscriptionRepository.findByUserId(userId);
    }

    @GetMapping("/status/{status}")
    public List<Subscription> getByStatus(
            @PathVariable SubscriptionStatus status) {

        return subscriptionRepository.findByStatus(status);
    }

    @PostMapping("/pay")
    public String paySubscription(
            @RequestParam("userId") Long userId,
            @RequestParam("planId") Long planId) {

        Double amount = 99.0;

        return paymentClient.authorizePayment(userId, amount);
    }
    @PutMapping("/{id}/cancel")
    public Subscription cancelSubscription(@PathVariable Long id) {

        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscription.setStatus(SubscriptionStatus.CANCELLED);

        return subscriptionRepository.save(subscription);
    }
}