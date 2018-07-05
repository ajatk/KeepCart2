
package com.rs.keepcart.salesReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesReport {

    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("paid_amount")
    @Expose
    private String paidAmount;
    @SerializedName("received_amount")
    @Expose
    private String receivedAmount;
    @SerializedName("billing_datetime")
    @Expose
    private String billingDatetime;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(String receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getBillingDatetime() {
        return billingDatetime;
    }

    public void setBillingDatetime(String billingDatetime) {
        this.billingDatetime = billingDatetime;
    }

}
