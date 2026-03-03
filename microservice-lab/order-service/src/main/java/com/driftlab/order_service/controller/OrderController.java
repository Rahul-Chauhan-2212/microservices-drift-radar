package com.driftlab.order_service.controller;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final RestTemplate restTemplate;
  private final DistributionSummary responseSizeSummary;


  public OrderController(RestTemplate restTemplate, MeterRegistry meterRegistry) {
    this.restTemplate = restTemplate;
    this.responseSizeSummary = DistributionSummary
        .builder("order.response.size.bytes")
        .description("Response payload size in bytes")
        .register(meterRegistry);
  }

  @GetMapping("/{id}")
  public Map<String, Object> getOrder(@PathVariable String id) throws InterruptedException {

    // Simulate latency drift
    Thread.sleep(100);

    Map user = restTemplate.getForObject(
        "http://user-service:8080/users/" + id,
        Map.class);

    Map payment = restTemplate.getForObject(
        "http://payment-service:8080/payments/" + id,
        Map.class);

    Map<String, Object> response = new HashMap<>();
    response.put("orderId", id);
    response.put("user", user);
    response.put("payment", payment);

    // Custom drift metric: response size
    int responseSize = response.toString().length();
    responseSizeSummary.record(responseSize);

    return response;
  }
}