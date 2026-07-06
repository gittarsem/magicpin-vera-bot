package com.magicpin.vera_bot.model.common;

import lombok.Data;

@Data
public class Subscription {

    private String status;
    private String plan;
    private Integer daysRemaining;
    private String renewedAt;

}