
package com.rs.keepcart.afterLogin.selectMagazines;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectMagazinesModelClass {

    @SerializedName("Magazine_List")
    @Expose
    private List<String> magazineList = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<String> getMagazineList() {
        return magazineList;
    }

    public void setMagazineList(List<String> magazineList) {
        this.magazineList = magazineList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
