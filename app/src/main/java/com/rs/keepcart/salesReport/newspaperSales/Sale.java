
package com.rs.keepcart.salesReport.newspaperSales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sale {

    @SerializedName("newspaper_id")
    @Expose
    private String newspaperId;
    @SerializedName("newspaper_Name")
    @Expose
    private String newspaperName;
    @SerializedName("total_sale")
    @Expose
    private String totalSale;
    @SerializedName("received_amount")
    @Expose
    private String receivedAmount;
    @SerializedName("billing_datetime")
    @Expose
    private String billingDatetime;

    public String getNewspaperId() {
        return newspaperId;
    }

    public void setNewspaperId(String newspaperId) {
        this.newspaperId = newspaperId;
    }

    public String getNewspaperName() {
        return newspaperName;
    }

    public void setNewspaperName(String newspaperName) {
        this.newspaperName = newspaperName;
    }

    public String getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(String totalSale) {
        this.totalSale = totalSale;
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
