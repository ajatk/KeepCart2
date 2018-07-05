
package com.rs.keepcart.dashboard.dashBoardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorPreviou {

    @SerializedName("created_datetime")
    @Expose
    private String createdDatetime;
    @SerializedName("previous_amount")
    @Expose
    private String previousAmount;

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(String previousAmount) {
        this.previousAmount = previousAmount;
    }

}
