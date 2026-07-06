package com.magicpin.vera_bot.dto;

public class ContextResponse {

    private boolean accepted;

    public ContextResponse(boolean accepted, String ackId, String storedAt) {
        this.accepted = accepted;
        this.ackId = ackId;
        this.storedAt = storedAt;
    }

    private String ackId;

    private String storedAt;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public String getStoredAt() {
        return storedAt;
    }

    public void setStoredAt(String storedAt) {
        this.storedAt = storedAt;
    }
}
