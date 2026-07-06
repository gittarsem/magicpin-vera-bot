package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.ai.AIService;
import com.magicpin.vera_bot.dto.ReplyRequest;
import com.magicpin.vera_bot.dto.ReplyResponse;
import com.magicpin.vera_bot.storage.ConversationStore;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final AIService aiService;
    private final ConversationStore conversationStore;

    public ReplyService(AIService aiService,
                        ConversationStore conversationStore) {
        this.aiService = aiService;
        this.conversationStore = conversationStore;
    }

    public ReplyResponse reply(ReplyRequest request) {

        System.out.println("====================================");
        System.out.println("Incoming Message : " + request.getMessage());

        conversationStore.addMessage(
                request.getConversationId(),
                "Merchant",
                request.getMessage()
        );

        String message = request.getMessage().toLowerCase().trim();

        System.out.println("Processed Message : " + message);

        // Merchant not interested
        if (message.contains("not interested")
                || message.contains("stop")
                || message.contains("bye")
                || message.equals("no")
                || message.startsWith("no")) {

            System.out.println("END CONDITION TRIGGERED");

            return new ReplyResponse(
                    "end",
                    null,
                    null,
                    "Merchant is not interested.",
                    null
            );
        }

        // Merchant wants later follow-up
        if (message.contains("later")
                || message.contains("busy")
                || message.contains("tomorrow")) {

            System.out.println("WAIT CONDITION TRIGGERED");

            return new ReplyResponse(
                    "wait",
                    null,
                    null,
                    "Merchant requested follow-up later.",
                    86400
            );
        }

        System.out.println("SEND CONDITION TRIGGERED");

        String history = conversationStore.getConversation(
                request.getConversationId()
        );

        String prompt = """
You are Vera, Magicpin's AI assistant.

Conversation History:

%s

Merchant's latest message:
%s

Instructions:
- Reply naturally.
- Help the merchant.
- Keep the reply under 80 words.
- End with one relevant question.
"""
                .formatted(
                        history,
                        request.getMessage()
                );

        String aiReply = aiService.generateMessage(prompt);

        conversationStore.addMessage(
                request.getConversationId(),
                "Vera",
                aiReply
        );

        return new ReplyResponse(
                "send",
                aiReply,
                "open_ended",
                "Conversation aware response",
                null
        );
    }
}