
package com.rs.keepcart.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModelClass {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("active_status")
    @Expose
    private Integer activeStatus;
    @SerializedName("Vendor_Name")
    @Expose
    private String vendorName;
    @SerializedName("Vendor_Email")
    @Expose
    private String vendorEmail;
    @SerializedName("Vendor_ID")
    @Expose
    private String vendorID;
    @SerializedName("Vendor_Mobile")
    @Expose
    private String vendorMobile;
    @SerializedName("Vendor_Job")
    @Expose
    private Object vendorJob;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginModelClass() {
    }

    /**
     * 
     * @param message
     * @param vendorEmail
     * @param vendorJob
     * @param vendorID
     * @param vendorName
     * @param status
     * @param activeStatus
     * @param vendorMobile
     */
    public LoginModelClass(String message, Integer status, Integer activeStatus, String vendorName, String vendorEmail, String vendorID, String vendorMobile, Object vendorJob) {
        super();
        this.message = message;
        this.status = status;
        this.activeStatus = activeStatus;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorID = vendorID;
        this.vendorMobile = vendorMobile;
        this.vendorJob = vendorJob;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorMobile() {
        return vendorMobile;
    }

    public void setVendorMobile(String vendorMobile) {
        this.vendorMobile = vendorMobile;
    }

    public Object getVendorJob() {
        return vendorJob;
    }

    public void setVendorJob(Object vendorJob) {
        this.vendorJob = vendorJob;
    }

}
