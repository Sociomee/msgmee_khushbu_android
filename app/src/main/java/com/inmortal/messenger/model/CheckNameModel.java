package com.inmortal.messenger.model;

public class CheckNameModel {
    public String getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(String userNameList) {
        this.userNameList = userNameList;
    }

    public CheckNameModel(String userNameList) {
        this.userNameList = userNameList;
    }

    private String userNameList;
}
