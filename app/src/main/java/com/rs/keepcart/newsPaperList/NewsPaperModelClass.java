
package com.rs.keepcart.newsPaperList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsPaperModelClass {

    @SerializedName("vendorname")
    @Expose
    private String vendorname;
    @SerializedName("vendorid")
    @Expose
    private String vendorid;
    @SerializedName("newspapers_details")
    @Expose
    private List<NewspapersDetail> newspapersDetails = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendorid() {
        return vendorid;
    }

    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }

    public List<NewspapersDetail> getNewspapersDetails() {
        return newspapersDetails;
    }

    public void setNewspapersDetails(List<NewspapersDetail> newspapersDetails) {
        this.newspapersDetails = newspapersDetails;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
