
package com.rs.keepcart.dashboard.dashBoardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DueAmounts {

    @SerializedName("billing_datetime")
    @Expose
    private String billingDatetime;
    @SerializedName("due_amount")
    @Expose
    private String dueAmount;

    public String getBillingDatetime() {
        return billingDatetime;
    }

    public void setBillingDatetime(String billingDatetime) {
        this.billingDatetime = billingDatetime;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

}
