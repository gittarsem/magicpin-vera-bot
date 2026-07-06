package com.magicpin.vera_bot.controller;

import com.magicpin.vera_bot.dto.ContextRequest;
import com.magicpin.vera_bot.dto.ContextResponse;
import com.magicpin.vera_bot.services.ContextService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ContextController {

    private final ContextService service;

    public ContextController(ContextService service) {
        this.service = service;
    }

    @PostMapping("/context")
    public ContextResponse save(@RequestBody ContextRequest request) {
        return service.save(request);
    }
}