
package com.rs.keepcart.salesReport.newspaperSales;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewspaperSalesModelClass {

    @SerializedName("Sales")
    @Expose
    private List<Sale> sales = null;
    @SerializedName("pending_amount")
    @Expose
    private List<PendingAmount> pendingAmount = null;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<PendingAmount> getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(List<PendingAmount> pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
