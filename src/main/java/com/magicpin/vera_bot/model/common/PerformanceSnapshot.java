package com.magicpin.vera_bot.model.common;

import lombok.Data;

@Data
public class PerformanceSnapshot {

    private Integer windowDays;

    private Integer views;

    private Integer calls;

    private Integer directions;

    private Double ctr;

    private Integer leads;

    private Delta7d delta7d;

}
