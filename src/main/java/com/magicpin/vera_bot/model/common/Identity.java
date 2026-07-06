package com.magicpin.vera_bot.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Identity {

    private String name;
    private String city;
    private String locality;
    private String placeId;
    private Boolean verified;
    private List<String> languages;
    private String ownerFirstName;
    private Integer establishedYear;

}
