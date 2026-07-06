package com.magicpin.vera_bot.storage;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConversationStore {

    private final Map<String, List<String>> conversations = new HashMap<>();

    private final Map<String, Integer> autoReplyCount = new HashMap<>();

    private final Map<String, String> lastBotReply = new HashMap<>();

    public void addMessage(String conversationId,
                           String role,
                           String message) {

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

    public int incrementAutoReply(String conversationId) {

        int count = autoReplyCount.getOrDefault(conversationId, 0) + 1;

        autoReplyCount.put(conversationId, count);

        return count;
    }

    public void resetAutoReply(String conversationId) {

        autoReplyCount.remove(conversationId);

    }

    public boolean isRepeatedReply(String conversationId, String reply) {

        String previous = lastBotReply.get(conversationId);

        if (reply.equals(previous)) {
            return true;
        }

        lastBotReply.put(conversationId, reply);

        return false;
    }

    public void clearConversation(String conversationId) {

        conversations.remove(conversationId);

        autoReplyCount.remove(conversationId);

        lastBotReply.remove(conversationId);
    }

}