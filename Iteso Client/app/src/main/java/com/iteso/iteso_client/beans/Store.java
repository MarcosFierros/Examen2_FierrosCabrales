package com.iteso.iteso_client.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable{

    private int id;
    private String name;
    private String phone;
    private int thumbnail;
    private double latitude;
    private double longitude;
    private City city;

    public Store() {}

    protected Store(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        thumbnail = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        city = in.readParcelable(City.class.getClassLoader());
    }

    @Override
    public String toString() {
        return getName() + " - " + getCity().getName();
    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeInt(thumbnail);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeParcelable(city, flags);
    }

}
