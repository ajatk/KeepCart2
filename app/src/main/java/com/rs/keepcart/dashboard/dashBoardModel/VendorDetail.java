
package com.rs.keepcart.dashboard.dashBoardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorDetail {

    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Due_amounts")
    @Expose
    private DueAmounts dueAmounts;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DueAmounts getDueAmounts() {
        return dueAmounts;
    }

    public void setDueAmounts(DueAmounts dueAmounts) {
        this.dueAmounts = dueAmounts;
    }

}
