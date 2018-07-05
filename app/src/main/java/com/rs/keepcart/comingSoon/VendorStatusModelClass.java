
package com.rs.keepcart.comingSoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorStatusModelClass {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("active_status")
    @Expose
    private String activeStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

}
