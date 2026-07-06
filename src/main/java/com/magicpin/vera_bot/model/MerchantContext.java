package com.magicpin.vera_bot.model;

import com.magicpin.vera_bot.model.common.Identity;
import com.magicpin.vera_bot.model.common.MerchantOffer;
import com.magicpin.vera_bot.model.common.PerformanceSnapshot;
import com.magicpin.vera_bot.model.common.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class MerchantContext {

    private String merchantId;

    private String categorySlug;

    private Identity identity;

    private Subscription subscription;

    private PerformanceSnapshot performance;

    private List<MerchantOffer> offers;

    private List<Object> conversationHistory;

    private Object customerAggregate;

    private List<String> signals;

    private List<Object> reviewThemes;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public PerformanceSnapshot getPerformance() {
        return performance;
    }

    public void setPerformance(PerformanceSnapshot performance) {
        this.performance = performance;
    }

    public List<MerchantOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<MerchantOffer> offers) {
        this.offers = offers;
    }

    public List<Object> getConversationHistory() {
        return conversationHistory;
    }

    public void setConversationHistory(List<Object> conversationHistory) {
        this.conversationHistory = conversationHistory;
    }

    public Object getCustomerAggregate() {
        return customerAggregate;
    }

    public void setCustomerAggregate(Object customerAggregate) {
        this.customerAggregate = customerAggregate;
    }

    public List<String> getSignals() {
        return signals;
    }

    public void setSignals(List<String> signals) {
        this.signals = signals;
    }

    public List<Object> getReviewThemes() {
        return reviewThemes;
    }

    public void setReviewThemes(List<Object> reviewThemes) {
        this.reviewThemes = reviewThemes;
    }

    public MerchantContext(String merchantId, String categorySlug, Identity identity, Subscription subscription, PerformanceSnapshot performance, List<MerchantOffer> offers, List<Object> conversationHistory, Object customerAggregate, List<String> signals, List<Object> reviewThemes) {
        this.merchantId = merchantId;
        this.categorySlug = categorySlug;
        this.identity = identity;
        this.subscription = subscription;
        this.performance = performance;
        this.offers = offers;
        this.conversationHistory = conversationHistory;
        this.customerAggregate = customerAggregate;
        this.signals = signals;
        this.reviewThemes = reviewThemes;
    }
}
