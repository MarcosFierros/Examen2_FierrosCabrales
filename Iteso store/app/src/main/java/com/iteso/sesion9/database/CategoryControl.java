package com.iteso.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Category;

import java.util.ArrayList;

public class CategoryControl {
    public static ArrayList<Category> getCategories(DatabaseHandler dh){
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT "+DatabaseHandler.KEY_CATEGORY_ID+", "
                +DatabaseHandler.KEY_CATEGORY_NAME
                +" FROM "+DatabaseHandler.TABLE_CATEGORY;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Boolean cursorStatus = cursor.moveToFirst();
        while(cursorStatus){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
            cursorStatus = cursor.moveToNext();
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){}
        db = null;
        cursor = null;
        return categories;
    }
}
