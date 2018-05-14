package com.rs.keepcart.model;

/**
 * Created by Dell on 10-01-2018.
 */

public class LoginInfoClass {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String socialId;
    private String LoginWith;
    private String phoneNumber;
    private String referralCode;
    private int redirectFlag;
    private String userId;
    private String profileImage;

    public void add(String userName, String firstName, String lastName, String email,
                    String password, String socialId, String loginWith, String phoneNumber,
                    String profileImage) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.socialId = socialId;
        LoginWith = loginWith;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSocialId() {
        return socialId;
    }

    public String getLoginWith() {
        return LoginWith;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public int getRedirectFlag() {
        return redirectFlag;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public void setRedirectFlag(int redirectFlag) {
        this.redirectFlag = redirectFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
