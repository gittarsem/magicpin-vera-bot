package com.magicpin.vera_bot.dto;


public class TickRequest {

    private String tickId;
    private String timestamp;

    public TickRequest() {}

    public String getTickId() {
        return tickId;
    }

    public void setTickId(String tickId) {
        this.tickId = tickId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
