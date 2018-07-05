
package com.rs.keepcart.afterLogin.selectNewspaper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class SelectedNewsPaperModelClass {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;

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


    private String reg_id;
    private Set<String> newspapers_id = null;

    public SelectedNewsPaperModelClass(String reg_id, Set<String> newspapers_id) {
        this.reg_id = reg_id;
        this.newspapers_id = newspapers_id;
    }

}
