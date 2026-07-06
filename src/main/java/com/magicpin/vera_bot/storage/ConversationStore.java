package com.magicpin.vera_bot.storage;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConversationStore {

    private final Map<String, List<String>> conversations = new HashMap<>();

    public void addMessage(String conversationId, String role, String message) {

        conversations
                .computeIfAbsent(conversationId, k -> new ArrayList<>())
                .add(role + ": " + message);
    }

    public String getConversation(String conversationId) {

        List<String> history = conversations.get(conversationId);

        if (history == null) {
            return "";
        }

        return String.join("\n", history);
    }
}