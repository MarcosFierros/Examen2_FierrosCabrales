package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;

import java.util.ArrayList;


public class StoreControl {
    public static void addStore(Store store, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHandler.KEY_STORE_NAME, store.getName());
        cv.put(DatabaseHandler.KEY_STORE_PHONE, store.getPhone());
        cv.put(DatabaseHandler.KEY_STORE_CITYID, store.getCity().getId());
        cv.put(DatabaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());
        cv.put(DatabaseHandler.KEY_STORE_LATITUDE, store.getLatitude());
        cv.put(DatabaseHandler.KEY_STORE_LONGITUDE, store.getLongitude());
        db.insert(DatabaseHandler.TABLE_STORE, null, cv);
        try{
            db.close();
        }catch (Exception e){}
        db = null;
        cv = null;
    }

    public static void addStore(ContentValues cv, DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.insert(DatabaseHandler.TABLE_STORE, null, cv);
        try{
            db.close();
        }catch (Exception e){}
        db = null;
        cv = null;
    }


    public static void deleteData(DatabaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.execSQL("DELETE FROM " + DatabaseHandler.TABLE_STORE);

    }
    public static ArrayList<Store> getStores(DatabaseHandler dh){
        ArrayList<Store> stores = new ArrayList<Store>();
        String selectQuery = "SELECT s."+ DatabaseHandler.KEY_STORE_ID+", "
                +"s."+ DatabaseHandler.KEY_STORE_NAME+", "
                +"s."+ DatabaseHandler.KEY_STORE_PHONE+", "
                +"s."+ DatabaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+ DatabaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+ DatabaseHandler.KEY_STORE_LONGITUDE+", "
                +"c."+ DatabaseHandler.KEY_CITY_ID+", "
                +"c."+ DatabaseHandler.KEY_CITY_NAME+" FROM "+ DatabaseHandler.TABLE_STORE+" s"
                +" INNER JOIN "+ DatabaseHandler.TABLE_CITY+" c ON "
                +"s."+ DatabaseHandler.KEY_STORE_CITYID+" = c."+ DatabaseHandler.KEY_CITY_ID;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean cursorStatus = cursor.moveToFirst();
        while(cursorStatus){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
            stores.add(store);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return stores;
    }

    public static Store getStoreById(int id, DatabaseHandler dh){
        Store store = null;
        String selectQuery = "SELECT s."+ DatabaseHandler.KEY_STORE_ID+", "
                +"s."+ DatabaseHandler.KEY_STORE_NAME+", "
                +"s."+ DatabaseHandler.KEY_STORE_PHONE+", "
                +"s."+ DatabaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+ DatabaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+ DatabaseHandler.KEY_STORE_LONGITUDE+", "
                +"c."+ DatabaseHandler.KEY_CITY_ID+", "
                +"c."+ DatabaseHandler.KEY_CITY_NAME+" FROM "+ DatabaseHandler.TABLE_STORE+" s"
                +" INNER JOIN "+ DatabaseHandler.TABLE_CITY+" c ON "
                +"s."+ DatabaseHandler.KEY_STORE_CITYID+" = c."+ DatabaseHandler.KEY_CITY_ID
                +" WHERE "+ DatabaseHandler.KEY_STORE_ID+" = "+id;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return store;
    }
}
