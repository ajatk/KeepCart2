package com.rs.keepcart.paytmSet;

import com.google.gson.annotations.SerializedName;
import com.paytm.pgsdk.Log;

import java.util.UUID;

/**
 * Created by sam on 6/13/2018.
 */

public class Paytm {

    @SerializedName("MID")
    String mId;

    @SerializedName("CUST_ID")
    String custId;

    @SerializedName("CHANNEL_ID")
    String channelId;

    @SerializedName("TXN_AMOUNT")
    String txnAmount;

    @SerializedName("WEBSITE")
    String website;

    @SerializedName("CALLBACK_URL")
    String callBackUrl;

    @SerializedName("INDUSTRY_TYPE_ID")
    String industryTypeId;

    String ORDER_ID;
    String TXN_AMOUNT;
    String CUST_ID;
    String EMAIL;
    String MOBILE_NO;

    public String getChecksumHash() {
        return checksumHash;
    }

    public void setChecksumHash(String checksumHash) {
        this.checksumHash = checksumHash;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaytStatus() {
        return paytStatus;
    }

    public void setPaytStatus(String paytStatus) {
        this.paytStatus = paytStatus;
    }

    @SerializedName("CHECKSUMHASH")
    private String checksumHash;

    @SerializedName("ORDER_ID")
    private String orderId;

    @SerializedName("payt_STATUS")
    private String paytStatus;



    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }



    public Paytm(  String TXN_AMOUNT, String CUST_ID, String EMAIL, String MOBILE_NO) {
        this.ORDER_ID =  generateString();
        this.TXN_AMOUNT = TXN_AMOUNT;
        this.CUST_ID = CUST_ID;
        this.MOBILE_NO = MOBILE_NO;
        this.EMAIL = EMAIL;
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getTXN_AMOUNT() {
        return TXN_AMOUNT;
    }

    public String getCUST_ID() {
        return CUST_ID;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public void setTXN_AMOUNT(String TXN_AMOUNT) {
        this.TXN_AMOUNT = TXN_AMOUNT;
    }

    public void setCUST_ID(String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }




    public Paytm(String mId, String channelId, String txnAmount, String website, String callBackUrl, String industryTypeId) {
        this.mId = mId;
        this.orderId = generateString();
        this.custId = generateString();
        this.channelId = channelId;
        this.txnAmount = txnAmount;
        this.website = website;
        this.callBackUrl = callBackUrl;
        this.industryTypeId = industryTypeId;

        Log.d("orderId", orderId);
        Log.d("customerId", custId);
        //ORDER_ID,TXN_AMOUNT,CUST_ID,,MOBILE_NO
    }

    public String getmId() {
        return mId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustId() {
        return custId;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getTxnAmount() {
        return txnAmount;
    }

    public String getWebsite() {
        return website;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public String getIndustryTypeId() {
        return industryTypeId;
    }

    /*
    * The following method we are using to generate a random string everytime
    * As we need a unique customer id and order id everytime
    * For real scenario you can implement it with your own application logic
    * */
    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
