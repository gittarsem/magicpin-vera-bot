package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.config.OpenRouterConfig;
import com.magicpin.vera_bot.dto.MetadataResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MetadataService {

    private final OpenRouterConfig config;

    public MetadataService(OpenRouterConfig config) {
        this.config = config;
    }

    public MetadataResponse metadata() {

        MetadataResponse response = new MetadataResponse();

        response.setTeamName("Tarsem Gulab");
        response.setTeamMembers(List.of("Tarsem Gulab"));
        response.setModel(config.getModel());
        response.setApproach("Spring Boot AI assistant with context management, conversation memory and OpenRouter LLM integration.");
        response.setContactEmail("work4tarsemgulab@gmail.com");
        response.setVersion("1.0.0");
        response.setSubmittedAt(Instant.now().toString());

        return response;
    }
}