package com.rs.keepcart.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sam on 4/26/2018.
 */

public class LoginInfoModel implements Parcelable {

    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private String accountNo;
    private String bankName;



    public LoginInfoModel(String name, String email, String password, String mobileNo, String accountNo, String bankName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.accountNo = accountNo;
        this.bankName = bankName;
    }

    protected LoginInfoModel(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        mobileNo = in.readString();
        accountNo = in.readString();
        bankName = in.readString();
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

    public String getPassword() {   return password;   }

    public String getMobileNo() {    return mobileNo;  }

    public String getAccountNo() {   return accountNo;  }

    public String getBankName() {    return bankName;  }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(mobileNo);
        dest.writeString(accountNo);
        dest.writeString(bankName);
    }
}
