package com.rs.keepcart.model;

import com.rs.keepcart.userlist.UserDetail;

import java.util.List;

/**
 * Created by sam on 5/17/2018.
 */

public class SetNewspaperInfoModelClass {

    private String vendorName;
    private String vendorid;
    private Integer status;

    private String id;
    private List<String> newspapersDetails = null;

    private List<UserDetail> userDetails = null;
    private String name;

    private String billAmount;
    private String serviceType;
    private String userimage;
    private String address;

    public SetNewspaperInfoModelClass(String id) {
        this.id = id;
    }

    public SetNewspaperInfoModelClass(String id, List<String> newspapersDetails) {
        this.id = id;
        this.newspapersDetails = newspapersDetails;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getUserimage() {
        return userimage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorid() {
        return vendorid;
    }

    public List<String> getNewspapersDetails() {
        return newspapersDetails;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
