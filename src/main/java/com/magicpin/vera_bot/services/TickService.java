package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.dto.TickAction;
import com.magicpin.vera_bot.dto.TickResponse;
import com.magicpin.vera_bot.model.MerchantContext;
import com.magicpin.vera_bot.storage.ContextStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TickService {

    private final ContextStore contextStore;
    private final ConversationService conversationService;

    public TickService(ContextStore contextStore,
                       ConversationService conversationService) {

        this.contextStore = contextStore;
        this.conversationService = conversationService;
    }
    public TickResponse tick() {

        List<TickAction> actions = new ArrayList<>();

        for (MerchantContext merchant : contextStore.getMerchants().values()) {
            if (merchant == null || merchant.getMerchantId() == null) {
                continue;
            }
            String message =
                    conversationService.generateOpeningMessage(merchant);

            TickAction action = new TickAction(
                    UUID.randomUUID().toString(),
                    merchant.getMerchantId(),
                    "customer-demo",
                    message
            );

            actions.add(action);
        }

        return new TickResponse(actions);
    }
}
