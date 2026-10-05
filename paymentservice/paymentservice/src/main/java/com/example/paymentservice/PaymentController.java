package com.example.paymentservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/authorize")
    public String authorizePayment(
            @RequestParam Long userId,
            @RequestParam Double amount) {

        if (amount > 0) {
            return "Payment authorized successfully";
        }

        return "Payment failed";
    }
}