package com.inmortal.messenger.model;

public class FriendsSyncModel {
    private String userId;
    private String mobile;
    private String userName;
    private String fullName;
    private String profileImage;
    private String isMessagmee;

    public FriendsSyncModel(String userId, String mobile, String userName, String fullName, String profileImage, String isMessagmee)
    {
        this.userId = userId;
        this.mobile = mobile;
        this.userName = userName;
        this.fullName = fullName;
        this.profileImage = profileImage;
        this.isMessagmee = isMessagmee;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getIsMessagmee() {
        return isMessagmee;
    }

    public void setIsMessagmee(String isMessagmee) {
        this.isMessagmee = isMessagmee;
    }


}
