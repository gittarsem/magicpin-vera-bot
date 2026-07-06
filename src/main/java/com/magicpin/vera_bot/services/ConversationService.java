package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.ai.AIService;
import com.magicpin.vera_bot.model.MerchantContext;
import com.magicpin.vera_bot.prompt.PromptBuilder;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final PromptBuilder promptBuilder;
    private final AIService aiService;

    public ConversationService(
            PromptBuilder promptBuilder,
            AIService aiService
    ){

        this.promptBuilder=promptBuilder;
        this.aiService=aiService;

    }

    public String generateOpeningMessage(
            MerchantContext merchant
    ){

        String prompt=
                promptBuilder.build(merchant);

        return aiService.generateMessage(prompt);

    }

}