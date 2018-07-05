package com.rs.keepcart.comingSoon;

/**
 * Created by sam on 5/29/2018.
 */

public class VendorStatusInfoModel {

    private int status;
    private int activeStatus;

    private String vendorID;

    public VendorStatusInfoModel(int status, int activeStatus, String vendorID) {
        this.status = status;
        this.activeStatus = activeStatus;
        this.vendorID = vendorID;
    }

    public VendorStatusInfoModel(String id_) {
        this.vendorID = id_;
    }

    public int getStatus() {
        return status;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }



}
