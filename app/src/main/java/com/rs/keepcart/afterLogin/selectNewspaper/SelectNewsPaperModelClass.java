
package com.rs.keepcart.afterLogin.selectNewspaper;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectNewsPaperModelClass {

    @SerializedName("Newspaper_List")
    @Expose
    private List<String> newspaperList = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<String> getNewspaperList() {
        return newspaperList;
    }

    public void setNewspaperList(List<String> newspaperList) {
        this.newspaperList = newspaperList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
