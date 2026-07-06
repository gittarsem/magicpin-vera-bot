package com.magicpin.vera_bot.prompt;

import com.magicpin.vera_bot.model.MerchantContext;
import com.magicpin.vera_bot.model.common.MerchantOffer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PromptBuilder {

    public String build(MerchantContext merchant) {

        String merchantName = "Merchant";
        String ownerName = "";
        String city = "";
        String category = "Business";

        if (merchant != null) {

            if (merchant.getCategorySlug() != null) {
                category = merchant.getCategorySlug();
            }

            if (merchant.getIdentity() != null) {

                if (merchant.getIdentity().getName() != null) {
                    merchantName = merchant.getIdentity().getName();
                }

                if (merchant.getIdentity().getOwnerFirstName() != null) {
                    ownerName = merchant.getIdentity().getOwnerFirstName();
                }

                if (merchant.getIdentity().getCity() != null) {
                    city = merchant.getIdentity().getCity();
                }
            }
        }

        String signals =
                merchant != null &&
                        merchant.getSignals() != null &&
                        !merchant.getSignals().isEmpty()
                        ? String.join(", ", merchant.getSignals())
                        : "No recent signals";

        String offers =
                merchant != null &&
                        merchant.getOffers() != null &&
                        !merchant.getOffers().isEmpty()
                        ? merchant.getOffers()
                        .stream()
                        .map(MerchantOffer::toString)
                        .collect(Collectors.joining(", "))
                        : "No active offers";

        String performance =
                merchant != null &&
                        merchant.getPerformance() != null
                        ? merchant.getPerformance().toString()
                        : "Performance data unavailable";

        return """
You are Vera, Magicpin's AI merchant engagement assistant.

Your goal is to proactively help merchants increase customer engagement.

Merchant Information
--------------------
Merchant Name: %s
Owner Name: %s
Business Category: %s
City: %s

Business Signals:
%s

Current Offers:
%s

Performance Snapshot:
%s

Instructions:
- Address the merchant by name.
- Personalize the message using the available context.
- If there are no active offers, suggest creating one.
- If performance looks weak, suggest one improvement.
- Mention only ONE actionable recommendation.
- Keep the message below 80 words.
- Sound professional, friendly and helpful.
- End with one engaging question.
- Never use placeholders like [Merchant Name].
- Return ONLY the final message.
"""
                .formatted(
                        merchantName,
                        ownerName,
                        category,
                        city,
                        signals,
                        offers,
                        performance
                );
    }
}