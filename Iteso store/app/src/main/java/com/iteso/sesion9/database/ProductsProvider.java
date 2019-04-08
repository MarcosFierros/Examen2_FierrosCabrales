package com.iteso.sesion9.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.ItemProduct;

import java.util.ArrayList;

public class ProductsProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.iteso.sesion9";
    static final int PRODUCTS_BY_CATEGORY = 1;
    static final int GET_CITIES = 2;
    static final int ADD_STORE = 3;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "products/category/#", PRODUCTS_BY_CATEGORY);
        uriMatcher.addURI(PROVIDER_NAME, "cities", GET_CITIES);
        uriMatcher.addURI(PROVIDER_NAME, "stores", ADD_STORE);
    }

    @Override
    public boolean onCreate() {
        DatabaseHandler.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        Log.e("URI_APP", uri.toString());
        switch (uriMatcher.match(uri)){
            case PRODUCTS_BY_CATEGORY:
                Log.e("URI_APP", uri.toString());
                int categoryId;
                try {
                    categoryId = Integer.parseInt(uri.getLastPathSegment());
                }catch (Exception e){
                    break;
                }
                String[] cursorColumns = new String[]{"id", "name", "description", "tienda", "ciudad", "telefono"};
                cursor = new MatrixCursor(cursorColumns);
                ArrayList<ItemProduct> products = ItemProductControl.getItemProductsByCategoryId(
                        categoryId, DatabaseHandler.getInstance(getContext()));
                Log.e("URI_APP", products.size()+"");
                for(ItemProduct product:products) {
                    ((MatrixCursor) cursor).newRow().add("id", product.getCode())
                            .add("name", product.getTitle())
                            .add("tienda", product.getStore().getName())
                            .add("telefono", product.getStore().getPhone());
                }
                break;
            case GET_CITIES:
                String[] cursorColumns2 = new String[]{"id", "name"};
                cursor = new MatrixCursor(cursorColumns2);
                ArrayList<City> cities =
                        CityControl.getCities(DatabaseHandler.getInstance(getContext()));

                for(City city: cities) {
                    Log.e("PRODUCTSPROVIDER", city.toString());
                    ((MatrixCursor) cursor).newRow().add("id", city.getId())
                            .add("name", city.getName());
                }
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case ADD_STORE:
                StoreControl.addStore(values, DatabaseHandler.getInstance(getContext()));
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, @Nullable String[] whereArgs) {
        return 0;
    }
}
