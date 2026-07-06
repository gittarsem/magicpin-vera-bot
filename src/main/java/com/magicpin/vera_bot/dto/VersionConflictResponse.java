package com.magicpin.vera_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class VersionConflictResponse {

    private boolean accepted;

    private String reason;

    private Integer currentVersion;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    public VersionConflictResponse(boolean accepted, String reason, Integer currentVersion) {
        this.accepted = accepted;
        this.reason = reason;
        this.currentVersion = currentVersion;
    }
}
