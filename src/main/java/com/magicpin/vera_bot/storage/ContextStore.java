package com.magicpin.vera_bot.storage;

import com.magicpin.vera_bot.model.CategoryContext;
import com.magicpin.vera_bot.model.CustomerContext;
import com.magicpin.vera_bot.model.MerchantContext;
import com.magicpin.vera_bot.model.TriggerContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ContextStore {

    private final ConcurrentHashMap<String, CategoryContext> categories = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, MerchantContext> merchants = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, CustomerContext> customers = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, TriggerContext> triggers = new ConcurrentHashMap<>();


    public Map<String, CategoryContext> getCategories() {
        return categories;
    }

    public Map<String, MerchantContext> getMerchants() {
        return merchants;
    }

    public Map<String, CustomerContext> getCustomers() {
        return customers;
    }

    public Map<String, TriggerContext> getTriggers() {
        return triggers;
    }
}
