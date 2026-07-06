package com.magicpin.vera_bot.dto;

public class ReplyResponse {

    private String action;

    private String body;

    private String cta;

    private String rationale;

    private Integer waitSeconds;

    public ReplyResponse(String action, String body, String cta, String rationale, Integer waitSeconds) {
        this.action = action;
        this.body = body;
        this.cta = cta;
        this.rationale = rationale;
        this.waitSeconds = waitSeconds;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCta() {
        return cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public Integer getWaitSeconds() {
        return waitSeconds;
    }

    public void setWaitSeconds(Integer waitSeconds) {
        this.waitSeconds = waitSeconds;
    }
}