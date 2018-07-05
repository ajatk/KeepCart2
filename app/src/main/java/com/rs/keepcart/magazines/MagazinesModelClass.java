
package com.rs.keepcart.magazines;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagazinesModelClass {

    @SerializedName("vendorname")
    @Expose
    private String vendorname;
    @SerializedName("vendorid")
    @Expose
    private String vendorid;
    @SerializedName("magzines_details")
    @Expose
    private List<MagzinesDetail> magzinesDetails = null;
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

    public List<MagzinesDetail> getMagzinesDetails() {
        return magzinesDetails;
    }

    public void setMagzinesDetails(List<MagzinesDetail> magzinesDetails) {
        this.magzinesDetails = magzinesDetails;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
