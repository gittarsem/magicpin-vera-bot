package com.magicpin.vera_bot.dto;

public class TickAction {

    private String conversationId;
    private String merchantId;
    private String customerId;
    private String message;

    public TickAction() {}

    public TickAction(String conversationId,
                      String merchantId,
                      String customerId,
                      String message) {
        this.conversationId = conversationId;
        this.merchantId = merchantId;
        this.customerId = customerId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}