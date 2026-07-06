package com.magicpin.vera_bot.dto;



import java.util.Map;

public class HealthResponse {

    private String status;

    public HealthResponse(String status, Map<String, Integer> contextsLoaded) {
        this.status = status;
        this.contextsLoaded = contextsLoaded;
    }

    private Map<String,Integer> contextsLoaded;

    public Map<String, Integer> getContextsLoaded() {
        return contextsLoaded;
    }

    public void setContextsLoaded(Map<String, Integer> contextsLoaded) {
        this.contextsLoaded = contextsLoaded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
