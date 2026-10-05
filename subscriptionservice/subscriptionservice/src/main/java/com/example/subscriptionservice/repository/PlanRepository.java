package com.example.subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.subscriptionservice.SubscriptionPlan;

public interface PlanRepository extends JpaRepository<SubscriptionPlan, Long> {
}