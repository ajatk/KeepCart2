
package com.rs.keepcart.magazines;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagzinesDetail implements Parcelable {

    @SerializedName("magzines_id")
    @Expose
    private String magzinesId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("getcount")
    @Expose
    private Integer getcount;

    protected MagzinesDetail(Parcel in) {
        magzinesId = in.readString();
        name = in.readString();
        images = in.readString();
        if (in.readByte() == 0) {
            getcount = null;
        } else {
            getcount = in.readInt();
        }
    }

    public static final Creator<MagzinesDetail> CREATOR = new Creator<MagzinesDetail>() {
        @Override
        public MagzinesDetail createFromParcel(Parcel in) {
            return new MagzinesDetail(in);
        }

        @Override
        public MagzinesDetail[] newArray(int size) {
            return new MagzinesDetail[size];
        }
    };

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

    public Integer getGetcount() {
        return getcount;
    }

    public void setGetcount(Integer getcount) {
        this.getcount = getcount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(magzinesId);
        dest.writeString(name);
        dest.writeString(images);
        if (getcount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(getcount);
        }
    }
}
