package com.inmortal.messenger.URL;

public class ApiURL {
    private static final String BASE_URL = "https://apiserver.msgmee.com/";
    public static final String Language_URL = BASE_URL+"public/getAllAppLanguages";
    public static final String Country_URL = BASE_URL+"public/getAllCountry";
    public static final String SendOTP_URL = BASE_URL+"public/sendOtp";
    public static final String OTPVerified_URL = BASE_URL+"public/loginWithMessagmee";
    public static final String CheckUsername_URL = BASE_URL+"public/userNameAvailable";
    public static final String UploadDP_URL = BASE_URL+"admin/uploadFile";
    public static final String EditProfile_URL = BASE_URL+"user/update";
    public static final String FriendSync_URL = BASE_URL+"user/syncContactList";
    public static final String GetChat_URL = BASE_URL+"messenger/getChatHeadList";
    public static final String Test_URL = "http://perfectdemo.xyz/eagle/old-jerusalem-restaurant/api/user/update/profile-photo";
}
