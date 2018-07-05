
package com.rs.keepcart.dashboard.dashBoardModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardModelClass {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("emailid")
    @Expose
    private String emailid;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("Banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("Category")
    @Expose
    private List<Category> category = null;
    @SerializedName("Vendor_details")
    @Expose
    private List<VendorDetail> vendorDetails = null;
    @SerializedName("Vendor_Previous")
    @Expose
    private List<VendorPreviou> vendorPrevious = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DashBoardModelClass() {
    }

    /**
     * 
     * @param vendorPrevious
     * @param category
     * @param status
     * @param userid
     * @param vendorDetails
     * @param banners
     * @param emailid
     * @param mobile
     */
    public DashBoardModelClass(String mobile, String emailid, String userid, List<Banner> banners, List<Category> category, List<VendorDetail> vendorDetails, List<VendorPreviou> vendorPrevious, Integer status) {
        super();
        this.mobile = mobile;
        this.emailid = emailid;
        this.userid = userid;
        this.banners = banners;
        this.category = category;
        this.vendorDetails = vendorDetails;
        this.vendorPrevious = vendorPrevious;
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<VendorDetail> getVendorDetails() {
        return vendorDetails;
    }

    public void setVendorDetails(List<VendorDetail> vendorDetails) {
        this.vendorDetails = vendorDetails;
    }

    public List<VendorPreviou> getVendorPrevious() {
        return vendorPrevious;
    }

    public void setVendorPrevious(List<VendorPreviou> vendorPrevious) {
        this.vendorPrevious = vendorPrevious;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
