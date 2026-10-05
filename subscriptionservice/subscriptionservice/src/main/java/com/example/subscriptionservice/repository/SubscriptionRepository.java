package com.example.subscriptionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscriptionservice.Subscription;
import com.example.subscriptionservice.SubscriptionStatus;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByStatus(SubscriptionStatus status);

    List<Subscription> findByUserId(Long userId);

}