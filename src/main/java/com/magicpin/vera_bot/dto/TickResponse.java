package com.magicpin.vera_bot.dto;

import java.util.List;

public class TickResponse {

    private List<TickAction> actions;

    public TickResponse() {}

    public TickResponse(List<TickAction> actions) {
        this.actions = actions;
    }

    public List<TickAction> getActions() {
        return actions;
    }

    public void setActions(List<TickAction> actions) {
        this.actions = actions;
    }
}
