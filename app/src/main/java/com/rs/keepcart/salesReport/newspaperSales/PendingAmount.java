
package com.rs.keepcart.salesReport.newspaperSales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingAmount {

    @SerializedName("pending_amount")
    @Expose
    private String pendingAmount;

    public String getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

}
