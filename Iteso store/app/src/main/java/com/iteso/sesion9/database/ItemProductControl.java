package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;

import java.util.ArrayList;

public class ItemProductControl {
    public static void addItemProduct(ItemProduct product, DatabaseHandler dh){
        long insertId = 0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHandler.KEY_PRODUCT_NAME, product.getTitle());
        cv.put(DatabaseHandler.KEY_PRODUCT_DESCRIPTION, product.getDescription());
        cv.put(DatabaseHandler.KEY_PRODUCT_IMAGE, product.getImage());
        cv.put(DatabaseHandler.KEY_PRODUCT_CATEGORYID, product.getCategory().getId());
        insertId=db.insert(DatabaseHandler.TABLE_PRODUCT, null, cv);
        if(insertId>0){
            ContentValues cv1 = new ContentValues();
            cv1.put(DatabaseHandler.KEY_STORE_PRODUCT_PRODUCTID, insertId);
            cv1.put(DatabaseHandler.KEY_STORE_PRODUCT_STOREID, product.getStore().getId());
            long h= db.insert(DatabaseHandler.TABLE_STORE_PRODUCT, null, cv1);
        }
        try{
            db.close();
        }catch (Exception e){}
        db = null;
        cv = null;
    }
    public static ArrayList<ItemProduct> getItemProductsByCategoryId(int categoryId, DatabaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<ItemProduct>();
        String selectQuery = "SELECT p."+DatabaseHandler.KEY_PRODUCT_ID+", "
                +"p."+DatabaseHandler.KEY_PRODUCT_NAME+", "
                +"p."+DatabaseHandler.KEY_PRODUCT_DESCRIPTION+", "
                +"p."+DatabaseHandler.KEY_PRODUCT_IMAGE+", "
                +"c."+DatabaseHandler.KEY_CATEGORY_ID+", "
                +"c."+DatabaseHandler.KEY_CATEGORY_NAME+", "
                +"s."+DatabaseHandler.KEY_STORE_ID+", "
                +"s."+DatabaseHandler.KEY_STORE_NAME+", "
                +"s."+DatabaseHandler.KEY_STORE_PHONE+", "
                +"s."+DatabaseHandler.KEY_STORE_THUMBNAIL+", "
                +"s."+DatabaseHandler.KEY_STORE_LATITUDE+", "
                +"s."+DatabaseHandler.KEY_STORE_LONGITUDE+", "
                +"ci."+DatabaseHandler.KEY_CITY_ID+", "
                +"ci."+DatabaseHandler.KEY_CITY_NAME+" "
                +"FROM "+DatabaseHandler.TABLE_STORE_PRODUCT+" sp "
                +"INNER JOIN "+DatabaseHandler.TABLE_PRODUCT+" p "
                +"ON sp."+DatabaseHandler.KEY_STORE_PRODUCT_PRODUCTID+" = p."+DatabaseHandler.KEY_PRODUCT_ID+" "
                +"INNER JOIN "+DatabaseHandler.TABLE_STORE+" s "
                +"ON sp."+DatabaseHandler.KEY_STORE_PRODUCT_STOREID+" = s."+DatabaseHandler.KEY_STORE_ID+" "
                +"INNER JOIN "+DatabaseHandler.TABLE_CITY+" ci "
                +"ON s."+DatabaseHandler.KEY_STORE_CITYID+" = ci."+DatabaseHandler.KEY_CITY_ID+" "
                +"INNER JOIN "+DatabaseHandler.TABLE_CATEGORY+" c "
                +"ON p."+DatabaseHandler.KEY_PRODUCT_CATEGORYID+" = c."+DatabaseHandler.KEY_CATEGORY_ID+" "
                +"WHERE c."+DatabaseHandler.KEY_CATEGORY_ID+" = "+categoryId;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean cursorStatus = cursor.moveToFirst();
        while(cursorStatus){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setDescription(cursor.getString(2));
            itemProduct.setImage(cursor.getInt(3));

            Category category = new Category();
            category.setId(cursor.getInt(4));
            category.setName(cursor.getString(5));
            itemProduct.setCategory(category);

            Store store = new Store();
            store.setId(cursor.getInt(6));
            store.setName(cursor.getString(7));
            store.setPhone(cursor.getString(8));
            store.setThumbnail(cursor.getInt(9));
            store.setLatitude(cursor.getDouble(10));
            store.setLongitude(cursor.getDouble(11));


            City city = new City();
            city.setId(cursor.getInt(12));
            city.setName(cursor.getString(13));

            store.setCity(city);
            itemProduct.setStore(store);

            products.add(itemProduct);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return products;
    }
}

