
package com.rs.keepcart.vendorUserList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("bill_amount")
    @Expose
    private String billAmount;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("userimage")
    @Expose
    private Object userimage;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Object getUserimage() {
        return userimage;
    }

    public void setUserimage(Object userimage) {
        this.userimage = userimage;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
