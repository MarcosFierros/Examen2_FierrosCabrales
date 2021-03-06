package com.iteso.iteso_client.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private int id;
    private String name;

    public City() {}

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public String toString() {
        return getName();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

}
