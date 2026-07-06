package com.magicpin.vera_bot.dto;


public class ReplyRequest {

    private String conversationId;

    private String merchantId;

    private String customerId;

    private String fromRole;

    private String message;

    private String receivedAt;

    private Integer turnNumber;

    public ReplyRequest(String conversationId, Integer turnNumber, String receivedAt, String message, String fromRole, String customerId, String merchantId) {
        this.conversationId = conversationId;
        this.turnNumber = turnNumber;
        this.receivedAt = receivedAt;
        this.message = message;
        this.fromRole = fromRole;
        this.customerId = customerId;
        this.merchantId = merchantId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFromRole() {
        return fromRole;
    }

    public void setFromRole(String fromRole) {
        this.fromRole = fromRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }


}