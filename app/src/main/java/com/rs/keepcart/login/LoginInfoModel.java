package com.rs.keepcart.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sam on 4/26/2018.
 */

public class LoginInfoModel implements Parcelable {

    private String name;
    private String email;
    private String userId;
    private String password;
    private String mobileNo;
    private String accountNo;
    private String bankName;
    private String vender;
    private String user;
    private String acountType;
    private String address;
    private String jobOccupation;
    private String latitude;
    private String longitude;
    private String stat_s;
    private String Vendor_Name;
    private String Vendor_Email;

    public String getVendor_Name() {
        return Vendor_Name;
    }

    public String getVendor_Email() {
        return Vendor_Email;
    }

    public String getVendor_Mobile() {
        return Vendor_Mobile;
    }

    public String getVendor_ID() {
        return Vendor_ID;
    }

    private String Vendor_Mobile;
    private String Vendor_ID;

    public LoginInfoModel(String name, String email,String password, String mobileNo, String latitude,
                          String longitude, String acountType,String jobOccupation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobOccupation = jobOccupation;
        this.acountType = acountType;
    }

    public LoginInfoModel(String Vendor_Email, String password){

        this.Vendor_Email = Vendor_Email;
        this.password = password;
    }

    protected LoginInfoModel(Parcel in) {
        name = in.readString();
        email = in.readString();
        userId = in.readString();
        password = in.readString();
        mobileNo = in.readString();
        accountNo = in.readString();
        bankName = in.readString();
        jobOccupation = in.readString();
        address = in.readString();
        vender = in.readString();
        user = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        stat_s = in.readString();
    }

    public static final Creator<LoginInfoModel> CREATOR = new Creator<LoginInfoModel>() {
        @Override
        public LoginInfoModel createFromParcel(Parcel in) {
            return new LoginInfoModel(in);
        }

        @Override
        public LoginInfoModel[] newArray(int size) {
            return new LoginInfoModel[size];
        }
    };

    public String getName() {    return name;  }

    public String getEmail() { return email;  }
    public String getUserId() { return userId;  }


    public String getPassword() {   return password;   }

    public String getMobileNo() {    return mobileNo;  }

    public String getAccountNo() {   return accountNo;  }

    public String getBankName() {    return bankName;  }
    public String getAcountType() {  return acountType;   }

    public String getAddress() {
        return address;
    }

    public String getJobOccupation() {
        return jobOccupation;
    }
    public String getLatitude() {   return latitude;   }

    public String getLongitude() { return longitude;  }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAcountType(String acountType) {
        this.acountType = acountType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJobOccupation(String jobOccupation) {
        this.jobOccupation = jobOccupation;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getStat_s() {     return stat_s;   }

    public void setStat_s(String stat_s) {    this.stat_s = stat_s;    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(userId);
        dest.writeString(password);
        dest.writeString(mobileNo);
        dest.writeString(accountNo);
        dest.writeString(bankName);
    }
}
