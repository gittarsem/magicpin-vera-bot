package com.magicpin.vera_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextRequest {

    private String scope;

    @JsonProperty("context_id")
    private String contextId;

    private Integer version;

    private Object payload;

    @JsonProperty("delivered_at")
    private String deliveredAt;

    public ContextRequest() {
    }

    public ContextRequest(String scope,
                          String contextId,
                          Integer version,
                          String deliveredAt,
                          Object payload) {
        this.scope = scope;
        this.contextId = contextId;
        this.version = version;
        this.deliveredAt = deliveredAt;
        this.payload = payload;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}