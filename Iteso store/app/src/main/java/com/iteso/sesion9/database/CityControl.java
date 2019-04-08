package com.iteso.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.City;

import java.util.ArrayList;

public class CityControl {

    public static ArrayList<City> getCities(DatabaseHandler dh) {

        ArrayList<City> cities = new ArrayList<>();
        String selectQuery = "SELECT " + DatabaseHandler.KEY_CITY_ID + ", "
                + DatabaseHandler.KEY_CITY_NAME
                + " FROM " + DatabaseHandler.TABLE_CITY;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                City city = new City();
                city.setId(cursor.getInt(0));
                city.setName(cursor.getString(1));
                cities.add(city);
                cursor.moveToNext();
            }
        }

        try {
            cursor.close();
            db.close();
        } catch (Exception e) {};
        db = null;
        cursor = null;

        return cities;
    }

}
