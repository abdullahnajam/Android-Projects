package com.fyp.quickrepair.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceDataModel implements Parcelable
{
    private String description,address,rating,charges,username,userphone,category;
    private long time;
    private String lattidute,longitude;

    protected ServiceDataModel(Parcel in) {
        description = in.readString();
        address = in.readString();
        rating = in.readString();
        charges = in.readString();
        username = in.readString();
        userphone = in.readString();
        category = in.readString();
        time = in.readLong();
        lattidute = in.readString();
        longitude = in.readString();
    }

    public static final Creator<ServiceDataModel> CREATOR = new Creator<ServiceDataModel>() {
        @Override
        public ServiceDataModel createFromParcel(Parcel in) {
            return new ServiceDataModel(in);
        }

        @Override
        public ServiceDataModel[] newArray(int size) {
            return new ServiceDataModel[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public String getCharges() {
        return charges;
    }

    public String getUsername() {
        return username;
    }

    public String getUserphone() {
        return userphone;
    }

    public String getCategory() {
        return category;
    }

    public long getTime() {
        return time;
    }

    public String getLattidute() {
        return lattidute;
    }

    public String getLongitude() {
        return longitude;
    }

    public ServiceDataModel(String description, String address, String rating, String charges, String username, String userphone, String category, long time, String lattidute, String longitude) {
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.charges = charges;
        this.username = username;
        this.userphone = userphone;
        this.category = category;
        this.time = time;
        this.lattidute = lattidute;
        this.longitude = longitude;
    }

    public ServiceDataModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(rating);
        dest.writeString(charges);
        dest.writeString(username);
        dest.writeString(userphone);
        dest.writeString(category);
        dest.writeLong(time);
        dest.writeString(lattidute);
        dest.writeString(longitude);
    }
}
