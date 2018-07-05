package com.rs.keepcart.afterLogin;

import android.os.Parcel;
import android.os.Parcelable;

import com.rs.keepcart.dashboard.dashBoardModel.Banner;
import com.rs.keepcart.dashboard.dashBoardModel.Category;
import com.rs.keepcart.dashboard.dashBoardModel.VendorDetail;

import org.json.JSONArray;

import java.util.List;
import java.util.Set;

/**
 * Created by sam on 6/2/2018.
 */

public class SelectedInfoModelClass implements Parcelable {

    private String id;
    private Set<String> set1 = null;

    public SelectedInfoModelClass(String id, Set<String> set1) {
        this.id = id;
        this.set1 = set1;
    }
    private List<String> newspapersDetails = null;

    public SelectedInfoModelClass(String id) {
        this.id = id;
    }

    public SelectedInfoModelClass(String id, JSONArray jsonArray) {
        this.id = id;
    }

    public SelectedInfoModelClass(String id, List<String> newspapersDetails) {
        this.id = id;
        this.newspapersDetails = newspapersDetails;
    }

    protected SelectedInfoModelClass(Parcel in) {
        id = in.readString();
        newspapersDetails = in.createStringArrayList();
        reg_id = in.readString();
        convertedString = in.readString();
        newspapers_i = in.createStringArrayList();
    }

    public static final Creator<SelectedInfoModelClass> CREATOR = new Creator<SelectedInfoModelClass>() {
        @Override
        public SelectedInfoModelClass createFromParcel(Parcel in) {
            return new SelectedInfoModelClass(in);
        }

        @Override
        public SelectedInfoModelClass[] newArray(int size) {
            return new SelectedInfoModelClass[size];
        }
    };

    public List<String> getNewspapersDetails() {
        return newspapersDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNewspapersDetails(List<String> newspapersDetails) {
        this.newspapersDetails = newspapersDetails;
    }

    private Set<String> newspapers_id = null;
    private String reg_id;
    private String convertedString;
    private List<String> newspapers_i = null;

    private List<Banner> banners = null;
    private List<Category> category = null;
    private List<VendorDetail> vendorDetails = null;

    public List<Banner> getBanners() {
        return banners;
    }

    public List<Category> getCategory() {
        return category;
    }

    public List<VendorDetail> getVendorDetails() {
        return vendorDetails;
    }


    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public void setVendorDetails(List<VendorDetail> vendorDetails) {
        this.vendorDetails = vendorDetails;
    }



    public List<String> getNewspapers_i() {
        return newspapers_i;
    }

    public SelectedInfoModelClass(String id_, String convertedString) {
        this.reg_id = id_;
        this.convertedString = convertedString;
    }

    /*public SelectedNewsPaperModelClass(String reg_id, Set<String> newspapers_id) {
        this.reg_id = reg_id;
        this.newspapers_id = newspapers_id;
    }
    public SelectedNewsPaperModelClass(String reg_id, List<String> newspapers_id) {

        this.newspapers_i = newspapers_id;
    }*/

    public String getReg_id() {
        return reg_id;
    }

    public Set<String> getNewspapers_id() {
        return newspapers_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public void setNewspapers_id(Set<String> newspapers_id) {
        this.newspapers_id = newspapers_id;
    }
    public void setNewspapers_i(List<String> newspapers_i) {
        this.newspapers_i = newspapers_i;
    }

    public String getConvertedString() {
        return convertedString;
    }

    public void setConvertedString(String convertedString) {
        this.convertedString = convertedString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeStringList(newspapersDetails);
        dest.writeString(reg_id);
        dest.writeString(convertedString);
        dest.writeStringList(newspapers_i);
    }
}
