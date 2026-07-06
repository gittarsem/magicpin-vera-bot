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

        String message = request.getMessage().toLowerCase().trim();

        conversationStore.addMessage(
                request.getConversationId(),
                "Merchant",
                request.getMessage()
        );

        /*
         * Detect WhatsApp auto replies
         */

        if (message.contains("thank you for contacting")
                || message.contains("our team will respond")
                || message.contains("automatic reply")
                || message.contains("auto reply")) {

            int count =
                    conversationStore.incrementAutoReply(
                            request.getConversationId()
                    );

            if (count == 1) {

                return new ReplyResponse(
                        "send",
                        "Looks like this is an automatic reply. When the owner is available, simply reply YES and I'll help you grow your Magicpin business.",
                        "binary_yes_no",
                        "Detected auto reply.",
                        null
                );

            }

            if (count == 2) {

                return new ReplyResponse(
                        "wait",
                        null,
                        null,
                        "Repeated auto reply detected.",
                        86400
                );

            }

            return new ReplyResponse(
                    "end",
                    null,
                    null,
                    "Auto reply received repeatedly. Ending conversation.",
                    null
            );

        }

        conversationStore.resetAutoReply(
                request.getConversationId()
        );

        /*
         * Merchant not interested
         */

        if (message.contains("not interested")
                || message.contains("stop")
                || message.contains("bye")
                || message.startsWith("no")) {

            return new ReplyResponse(
                    "end",
                    null,
                    null,
                    "Merchant is not interested.",
                    null
            );

        }

        /*
         * Merchant busy
         */

        if (message.contains("busy")
                || message.contains("later")
                || message.contains("tomorrow")) {

            return new ReplyResponse(
                    "wait",
                    null,
                    null,
                    "Merchant requested follow-up later.",
                    86400
            );

        }

        /*
         * Merchant ready
         */

        if (message.contains("yes")
                || message.contains("let's do it")
                || message.contains("lets do it")
                || message.contains("go ahead")
                || message.contains("interested")) {

            String history =
                    conversationStore.getConversation(
                            request.getConversationId()
                    );

            String prompt = """
You are Vera, Magicpin's AI assistant.

Conversation History:

%s

Merchant has shown interest.

Guide the merchant through the next onboarding step.

Keep reply under 80 words.
""".formatted(history);

            String aiReply =
                    aiService.generateMessage(prompt);

            conversationStore.addMessage(
                    request.getConversationId(),
                    "Vera",
                    aiReply
            );

            return new ReplyResponse(
                    "send",
                    aiReply,
                    "open_ended",
                    "Merchant interested.",
                    null
            );

        }

        /*
         * Default AI response
         */

        String history =
                conversationStore.getConversation(
                        request.getConversationId()
                );

        String prompt = """
You are Vera.

Conversation History:

%s

Reply naturally.

Keep reply below 80 words.

End with one question.
""".formatted(history);

        String aiReply =
                aiService.generateMessage(prompt);

        if (conversationStore.isRepeatedReply(
                request.getConversationId(),
                aiReply)) {

            aiReply = aiReply +
                    "\n\nWould you like me to explain this in more detail?";
        }

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