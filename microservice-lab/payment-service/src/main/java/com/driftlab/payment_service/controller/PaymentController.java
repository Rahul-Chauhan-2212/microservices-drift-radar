package com.driftlab.payment_service.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @GetMapping("/{orderId}")
  public Map<String, Object> getPayment(@PathVariable String orderId) {

    Map<String, Object> payment = new HashMap<>();
    payment.put("orderId", orderId);
    payment.put("status", "SUCCESS");
    payment.put("amount", 1200);

    return payment;
  }
}