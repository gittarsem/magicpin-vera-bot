package com.magicpin.vera_bot.controller;

import com.magicpin.vera_bot.dto.MetadataResponse;
import com.magicpin.vera_bot.services.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MetadataController {

    private final MetadataService service;
    public MetadataController(MetadataService service) {
        this.service = service;
    }

    @GetMapping("/metadata")
    public MetadataResponse metadata(){

        return service.metadata();

    }

}
