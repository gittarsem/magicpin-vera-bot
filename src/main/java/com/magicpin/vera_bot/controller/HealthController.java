package com.magicpin.vera_bot.controller;

import com.magicpin.vera_bot.dto.HealthResponse;
import com.magicpin.vera_bot.services.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HealthController {

    private final HealthService service;

    public HealthController(HealthService service) {
        this.service = service;
    }

    @GetMapping("/healthz")
    public HealthResponse health() {
        return service.health();
    }
}