package com.iteso.iteso_client.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Oscar Vargas
 * @since 26/02/18.
 *
 * @version 1.0.1 Add Parcelable Interfaz and code parameter
 */

public class ItemProduct implements Parcelable {
    private String title;
    private String location;
    private String phone;
    private String description;
    private String store;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public ItemProduct() {
    }

    public ItemProduct(String title, String location, String phone, String description, String store) {
        this.title = title;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.store = store;
    }

    protected ItemProduct(Parcel in) {
        title = in.readString();
        location = in.readString();
        phone = in.readString();
        description = in.readString();
        store = in.readString();
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel in) {
            return new ItemProduct(in);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", store='" + store + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(location);
        parcel.writeString(phone);
        parcel.writeString(description);
        parcel.writeString(store);
    }
}
