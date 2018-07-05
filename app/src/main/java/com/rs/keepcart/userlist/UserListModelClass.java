
package com.rs.keepcart.userlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListModelClass {

    @SerializedName("Vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("Vendor_Name")
    @Expose
    private String vendorName;
    @SerializedName("User_details")
    @Expose
    private List<UserDetail> userDetails = null;
    @SerializedName("status")
    @Expose
    private int status;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
