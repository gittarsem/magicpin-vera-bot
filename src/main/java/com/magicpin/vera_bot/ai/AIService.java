package com.magicpin.vera_bot.ai;

import com.magicpin.vera_bot.config.OpenRouterConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final RestClient restClient;
    private final OpenRouterConfig config;

    public AIService(OpenRouterConfig config) {
        this.config = config;
        this.restClient = RestClient.create();
    }

    @SuppressWarnings("unchecked")
    public String generateMessage(String prompt) {

        try {

            Map<String, Object> request = Map.of(
                    "model", config.getModel(),
                    "messages", List.of(
                            Map.of(
                                    "role", "user",
                                    "content", prompt
                            )
                    )
            );

            Map<String, Object> response =
                    restClient.post()
                            .uri("https://openrouter.ai/api/v1/chat/completions")
                            .header("Authorization", "Bearer " + config.getApiKey())
                            .header("HTTP-Referer", "http://localhost:8080")
                            .header("X-Title", "MagicPin Vera Bot")
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(request)
                            .retrieve()
                            .body(Map.class);

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) response.get("choices");

            Map<String, Object> message =
                    (Map<String, Object>) choices.get(0).get("message");

            return message.get("content").toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "AI Error : " + e.getMessage();
        }
    }
}