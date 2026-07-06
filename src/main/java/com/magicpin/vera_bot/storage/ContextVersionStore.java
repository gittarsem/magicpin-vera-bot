package com.magicpin.vera_bot.storage;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ContextVersionStore {

    private final ConcurrentHashMap<String,Integer> versions =
            new ConcurrentHashMap<>();

    public Integer getVersion(String contextId){

        return versions.get(contextId);

    }

    public void putVersion(String contextId,Integer version){

        versions.put(contextId,version);

    }

}
