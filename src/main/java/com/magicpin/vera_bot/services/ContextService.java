package com.magicpin.vera_bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicpin.vera_bot.dto.ContextRequest;
import com.magicpin.vera_bot.dto.ContextResponse;
import com.magicpin.vera_bot.exception.DuplicateVersionException;
import com.magicpin.vera_bot.exception.InvalidScopeException;
import com.magicpin.vera_bot.exception.StaleVersionException;
import com.magicpin.vera_bot.model.CategoryContext;
import com.magicpin.vera_bot.model.CustomerContext;
import com.magicpin.vera_bot.model.MerchantContext;
import com.magicpin.vera_bot.model.TriggerContext;
import com.magicpin.vera_bot.storage.ContextStore;
import com.magicpin.vera_bot.storage.ContextVersionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ContextService {

    private final ContextStore contextStore;
    private final ContextVersionStore versionStore;
    private final ObjectMapper objectMapper;

    public ContextService(ContextStore contextStore,
                          ContextVersionStore versionStore,
                          ObjectMapper objectMapper) {
        this.contextStore = contextStore;
        this.versionStore = versionStore;
        this.objectMapper = objectMapper;
    }

    public ContextResponse save(ContextRequest request) {

        validateVersion(request);

        switch (request.getScope()) {

            case "category" -> saveCategory(request);

            case "merchant" -> saveMerchant(request);

            case "customer" -> saveCustomer(request);

            case "trigger" -> saveTrigger(request);

            default -> throw new InvalidScopeException();

        }

        // Update version only after successful save
        versionStore.putVersion(
                request.getContextId(),
                request.getVersion()
        );

        return new ContextResponse(
                true,
                "ack_" + request.getContextId(),
                Instant.now().toString()
        );
    }

    /**
     * Validate incoming version.
     */
    private void validateVersion(ContextRequest request) {

        Integer currentVersion =
                versionStore.getVersion(request.getContextId());

        if (currentVersion == null) {
            return;
        }

        if (request.getVersion() < currentVersion) {
            throw new StaleVersionException();
        }

        // Same version = idempotent request
        if (request.getVersion().equals(currentVersion)) {
            throw new DuplicateVersionException();
        }
    }

    private void saveCategory(ContextRequest request) {

        CategoryContext category =
                objectMapper.convertValue(
                        request.getPayload(),
                        CategoryContext.class
                );

        contextStore.getCategories()
                .put(request.getContextId(), category);
    }

    private void saveMerchant(ContextRequest request) {

        MerchantContext merchant =
                objectMapper.convertValue(
                        request.getPayload(),
                        MerchantContext.class
                );

        contextStore.getMerchants()
                .put(request.getContextId(), merchant);
    }

    private void saveCustomer(ContextRequest request) {

        CustomerContext customer =
                objectMapper.convertValue(
                        request.getPayload(),
                        CustomerContext.class
                );

        contextStore.getCustomers()
                .put(request.getContextId(), customer);
    }

    private void saveTrigger(ContextRequest request) {

        TriggerContext trigger =
                objectMapper.convertValue(
                        request.getPayload(),
                        TriggerContext.class
                );

        contextStore.getTriggers()
                .put(request.getContextId(), trigger);
    }
}