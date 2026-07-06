package com.magicpin.vera_bot.services;

import com.magicpin.vera_bot.dto.HealthResponse;
import com.magicpin.vera_bot.storage.ContextStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HealthService {

    private final ContextStore store;
    public HealthService(ContextStore store){
        this.store=store;
    }
    public HealthResponse health() {

        Map<String,Integer> counts=new HashMap<>();

        counts.put(
                "category",
                store.getCategories().size()
        );

        counts.put(
                "merchant",
                store.getMerchants().size()
        );

        counts.put(
                "customer",
                store.getCustomers().size()
        );

        counts.put(
                "trigger",
                store.getTriggers().size()
        );

        return new HealthResponse(
                "ok",
                counts
        );

    }

}
