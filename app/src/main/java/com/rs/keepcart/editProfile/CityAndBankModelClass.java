package com.rs.keepcart.editProfile;

/**
 * Created by sam on 6/21/2018.
 */

public class CityAndBankModelClass {



    String city;
    String bank;
    String email_;
    String name_;
    String mobileNo_;
    String address_;
    String accountNo_;

    String ifscCode_;
    String sector_;
    String panCard;
    String adharCard;
    String pinCode_;

    public CityAndBankModelClass(String city, String bank, String email_, String name_, String mobileNo_,
                                 String address_, String accountNo_, String ifscCode_,
                                 String sector_, String panCard, String adharCard, String pinCode_) {
        this.city = city;
        this.bank = bank;
        this.email_ = email_;
        this.name_ = name_;
        this.mobileNo_ = mobileNo_;
        this.address_ = address_;
        this.accountNo_ = accountNo_;
        this.ifscCode_ = ifscCode_;
        this.sector_ = sector_;
        this.panCard = panCard;
        this.adharCard = adharCard;
        this.pinCode_ = pinCode_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public void setMobileNo_(String mobileNo_) {
        this.mobileNo_ = mobileNo_;
    }

    public void setAddress_(String address_) {
        this.address_ = address_;
    }

    public void setAccountNo_(String accountNo_) {
        this.accountNo_ = accountNo_;
    }

    public void setIfscCode_(String ifscCode_) {
        this.ifscCode_ = ifscCode_;
    }

    public void setSector_(String sector_) {
        this.sector_ = sector_;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    public void setPinCode_(String pinCode_) {
        this.pinCode_ = pinCode_;
    }



    public String getEmail_() {
        return email_;
    }

    public String getName_() {
        return name_;
    }

    public String getMobileNo_() {
        return mobileNo_;
    }

    public String getAddress_() {
        return address_;
    }

    public String getAccountNo_() {
        return accountNo_;
    }

    public String getIfscCode_() {
        return ifscCode_;
    }

    public String getSector_() {
        return sector_;
    }

    public String getPanCard() {
        return panCard;
    }

    public String getAdharCard() {
        return adharCard;
    }

    public String getPinCode_() {
        return pinCode_;
    }




    public CityAndBankModelClass(String city) {
        this.city = city;
        this.bank = city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    public String getCity() {
        return city;
    }

    public String getBank() {
        return bank;
    }
    @Override
    public String toString() {
        return city;
    }

}
