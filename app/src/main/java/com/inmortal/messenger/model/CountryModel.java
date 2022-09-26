package com.inmortal.messenger.model;

public class CountryModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    public String getTeleCoded() {
        return teleCoded;
    }

    public void setTeleCoded(String teleCoded) {
        this.teleCoded = teleCoded;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    private String id;
    private String name;
    private String code;
    private String flagURL;
    private String teleCoded;
    private String isDefault;



    public CountryModel(String id, String name,String code,String flagURL,String teleCode,String isDefault) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.flagURL = flagURL;
        this.teleCoded = teleCode;
        this.isDefault = isDefault;
    }
}
