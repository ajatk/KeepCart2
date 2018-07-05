package com.rs.keepcart.magazines;

/**
 * Created by sam on 5/18/2018.
 */

public class SetMagazinesInfoClass  {
    private String vendorName;
    private String vendorid;
    private String iD;
    private String name;
    private String image;
    private String stat_us;

    public SetMagazinesInfoClass(String vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorid() {
        return vendorid;
    }

    public String getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStat_us() { return stat_us;   }
    public void setStat_us(String stat_us) {  this.stat_us = stat_us;  }

}
