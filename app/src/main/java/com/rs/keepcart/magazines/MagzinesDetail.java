
package com.rs.keepcart.magazines;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagzinesDetail {

    @SerializedName("magzines_id")
    @Expose
    private String magzinesId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("getcount")
    @Expose
    private int getcount;
    @SerializedName("usercount")
    @Expose
    private int usercount;
    /**
     * No args constructor for use in serialization
     * 
     */
    public MagzinesDetail() {
    }

    /**
     * 
     * @param getcount
     * @param price
     * @param name
     * @param images
     * @param magzinesId
     * @param rating
     */
    public MagzinesDetail(String magzinesId, String name, String images, String price, String rating,int usercount, int getcount) {
        super();
        this.magzinesId = magzinesId;
        this.name = name;
        this.images = images;
        this.price = price;
        this.rating = rating;
        this.usercount = usercount;
        this.getcount = getcount;
    }

    public String getMagzinesId() {
        return magzinesId;
    }

    public void setMagzinesId(String magzinesId) {
        this.magzinesId = magzinesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public int getUsercount() {
        return usercount;
    }

    public void setUsercount(int usercount) {
        this.usercount = usercount;
    }

    public int getGetcount() {
        return getcount;
    }

    public void setGetcount(int getcount) {
        this.getcount = getcount;
    }

}
