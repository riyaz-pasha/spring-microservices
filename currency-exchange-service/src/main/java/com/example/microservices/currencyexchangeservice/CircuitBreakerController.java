package com.example.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "fallbackResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String sampleApi() {
        logger.info("sample-api request received");
        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
        return entity.getBody();
    }

    public String fallbackResponse(Exception ex) {
        return "Fallback Response";
    }
}
