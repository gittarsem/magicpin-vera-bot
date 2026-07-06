package com.magicpin.vera_bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenRouterConfig {

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String model;

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }
}