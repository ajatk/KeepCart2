package com.rs.keepcart.paytmSet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 6/13/2018.
 */

public class Checksum {

    @SerializedName("CHECKSUMHASH")
    private String checksumHash;

    @SerializedName("ORDER_ID")
    private String orderId;

    @SerializedName("payt_STATUS")
    private String paytStatus;
    private String MID;
    private String INDUSTRY_TYPE_ID;
    private String CHANNEL_ID;
    private String WEBSITE;
    private String TXN_AMOUNT;

    public void setPaytStatus(String paytStatus) {
        this.paytStatus = paytStatus;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getINDUSTRY_TYPE_ID() {
        return INDUSTRY_TYPE_ID;
    }

    public void setINDUSTRY_TYPE_ID(String INDUSTRY_TYPE_ID) {
        this.INDUSTRY_TYPE_ID = INDUSTRY_TYPE_ID;
    }

    public String getCHANNEL_ID() {
        return CHANNEL_ID;
    }

    public void setCHANNEL_ID(String CHANNEL_ID) {
        this.CHANNEL_ID = CHANNEL_ID;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public void setWEBSITE(String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }

    public String getTXN_AMOUNT() {
        return TXN_AMOUNT;
    }

    public void setTXN_AMOUNT(String TXN_AMOUNT) {
        this.TXN_AMOUNT = TXN_AMOUNT;
    }

    public String getCUST_ID() {
        return CUST_ID;
    }

    public void setCUST_ID(String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getCALLBACK_URL() {
        return CALLBACK_URL;
    }

    public void setCALLBACK_URL(String CALLBACK_URL) {
        this.CALLBACK_URL = CALLBACK_URL;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }


    private String CUST_ID;
    private String MOBILE_NO;
    private String CALLBACK_URL;
    private String EMAIL;

    public Checksum(String checksumHash, String orderId, String paytStatus) {
        this.checksumHash = checksumHash;
        this.orderId = orderId;
        this.paytStatus = paytStatus;
    }

    public String getChecksumHash() {
        return checksumHash;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaytStatus() {
        return paytStatus;
    }
}
