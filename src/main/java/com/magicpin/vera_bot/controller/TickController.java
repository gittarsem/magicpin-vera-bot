package com.magicpin.vera_bot.controller;


import com.magicpin.vera_bot.dto.TickRequest;
import com.magicpin.vera_bot.dto.TickResponse;
import com.magicpin.vera_bot.services.TickService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TickController {

    private final TickService service;

    public TickController(TickService service) {
        this.service = service;
    }

    @PostMapping("/tick")
    public TickResponse tick(@RequestBody TickRequest request) {
        return service.tick();
    }
}
