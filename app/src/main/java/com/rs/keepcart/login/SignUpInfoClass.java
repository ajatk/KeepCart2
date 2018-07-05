package com.rs.keepcart.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sam on 5/28/2018.
 */

public class SignUpInfoClass {

    public void setAcountType(String acountType) {
        this.acountType = acountType;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setJobOccupation(String jobOccupation) {
        this.jobOccupation = jobOccupation;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String acountType;

    public SignUpInfoClass(String acountType, String mobileNo, String name,
                           String email, String password, String latitude, String longitude, String jobOccupation) {
        this.acountType = acountType;
        this.mobileNo = mobileNo;
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobOccupation = jobOccupation;
    }

    private String mobileNo;
    private String name;
    private String email;
    private String password;
    private String latitude;
    private String longitude;
    private String jobOccupation;
    private String userId;



  /*  public SignUpInfoClass(String acountType, String mobileNo, String name, String email,String password, String latitude,
                          String longitude, String jobOccupation) {
        this.acountType = acountType;
        this.mobileNo = mobileNo;
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobOccupation = jobOccupation;*/


    public String getAcountType() {
        return acountType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getJobOccupation() {
        return jobOccupation;
    }

    public String getUserId() {
        return userId;
    }
   /* public void setAcountType(String acountType) {        this.acountType = acountType;    }

    public String getAcountType() {  return acountType;   }
    public String getMobileNo() {    return mobileNo;  }
    public String getName() {    return name;  }
    public void setName(String name) {        this.name = name;    }

    public String getEmail() { return email;  }
    public void setEmail(String email) {        this.email = email;    }

    public String getPassword() {   return password;   }
    public String getJobOccupation() {
        return jobOccupation;
    }
    public String getLatitude() {   return latitude;   }
    public String getLongitude() { return longitude;  }

    public String getUserId() { return userId;  }

    public void setUserId(String userId) {        this.userId = userId;    }



    public void setMobileNo(String mobileNo) {        this.mobileNo = mobileNo;    }

    public void setJobOccupation(String jobOccupation) {    this.jobOccupation = jobOccupation;    }

    public void setLatitude(String latitude) {    this.latitude = latitude;    }

    public void setLongitude(String longitude) {        this.longitude = longitude;    }
*/

}
