package com.iteso.iteso_client.beans;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.iteso.iteso_client.tools.Constant;

import java.util.ArrayList;

public class CityResolver {


    public static ArrayList<City> getCities(Context context) {

        ArrayList<City> cities = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();

        Uri uri = Uri.parse("content://" + Constant.PROVIDER_NAME + "/cities");
        String[] projection = new String[]{"id", "name"};
        Cursor cursor =
                resolver.query(uri,
                        projection,
                        null,
                        null,
                        null);


        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    City city = new City();
                    city.setId(cursor.getInt(0));
                    city.setName(cursor.getString(1));
                    cities.add(city);
                }while (cursor.moveToNext());
            }
        }else {

        }
        return cities;
    }


}
