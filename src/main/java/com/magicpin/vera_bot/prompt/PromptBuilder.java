package com.magicpin.vera_bot.prompt;

import com.magicpin.vera_bot.model.MerchantContext;
import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String build(MerchantContext merchant) {

        String merchantId =
                merchant != null ? merchant.getMerchantId() : "Unknown";

        return """
                You are Vera, an AI assistant for Magicpin.

                Your goal is to help merchants improve customer engagement.

                Merchant ID:
                %s

                Write one friendly business message.

                Rules:
                - Less than 60 words
                - Professional
                - Positive
                - Actionable
                - No emojis
                """.formatted(merchantId);
    }
}