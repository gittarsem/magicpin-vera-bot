package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.dto.MetadataResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MetadataService {

    public MetadataResponse metadata() {

        MetadataResponse response = new MetadataResponse();

        response.setTeamName("Your Team");
        response.setTeamMembers(List.of("Tarsem"));
        response.setModel("GPT-4.1");
        response.setApproach("LLM Context Composer");
        response.setContactEmail("work4tarsemgulab@email.com");
        response.setVersion("1.0.0");
        response.setSubmittedAt(Instant.now().toString());

        return response;
    }
}