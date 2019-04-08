package com.iteso.iteso_client.beans;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.iteso.iteso_client.tools.Constant;

import java.util.ArrayList;

public class StoreResolver {



    public static void addStore(Store store, Context context) {

        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://" + Constant.PROVIDER_NAME + "/stores");

        ContentValues cv = new ContentValues();
        cv.put(Constant.KEY_STORE_NAME, store.getName());
        cv.put(Constant.KEY_STORE_PHONE, store.getPhone());
        cv.put(Constant.KEY_STORE_CITYID, store.getCity().getId());
        cv.put(Constant.KEY_STORE_THUMBNAIL, store.getThumbnail());
        cv.put(Constant.KEY_STORE_LATITUDE, store.getLatitude());
        cv.put(Constant.KEY_STORE_LONGITUDE, store.getLongitude());

        resolver.insert(uri, cv);

        cv = null;

    }

}
