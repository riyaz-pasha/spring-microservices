package com.example.microservices.limitsservice.controller;

import com.example.microservices.limitsservice.model.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(1, 1000);
    }
}
