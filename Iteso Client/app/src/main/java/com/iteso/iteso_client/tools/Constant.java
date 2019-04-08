package com.iteso.iteso_client.tools;

public class Constant {

    public static final int TYPE_MAC = 0;
    public static final int TYPE_ALIENWARE = 1;
    public static final int TYPE_SHEETS = 2;
    public static final int TYPE_PILLOW = 3;
    public static final int TYPE_REFRIGERATOR = 4;
    public static final int TYPE_MICRO = 5;

    public static final String EXTRA_PRODUCT = "PRODUCT";
    public static final String EXTRA_FRAGMENT = "FRAGMENT";
    public static final int FRAGMENT_TECHNOLOGY = 0;
    public static final int FRAGMENT_HOME = 1;
    public static final int FRAGMENT_ELECTRONICS = 2;

    public static final int ACTIVITY_DETAIL = 9999;
    public static final int INTENT_STORES_NOTIFY = 9998;

    public static final String USER_PREFERENCES = "com.iteso.USER_PREFERENCES";

    //DATABASE TABLES
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_STORE_PRODUCT = "Store_Product";
    //CITY TABLE
    public static final String KEY_CITY_ID = "id";
    public static final String KEY_CITY_NAME = "name";
    //CATEGORY TABLE
    public static final String KEY_CATEGORY_ID = "id";
    public static final String KEY_CATEGORY_NAME = "name";
    //STORE TABLE
    public static final String KEY_STORE_ID = "id";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_CITYID = "City_id";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LATITUDE = "latitude";
    public static final String KEY_STORE_LONGITUDE = "longitude";
    //PRODUCT TABLE
    public static final String KEY_PRODUCT_ID = "id";
    public static final String KEY_PRODUCT_NAME = "name";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_CATEGORYID = "Category_id";
    //STORE-PRODUCT TABLE
    public static final String KEY_STORE_PRODUCT_ID = "id";
    public static final String KEY_STORE_PRODUCT_PRODUCTID = "Product_id";
    public static final String KEY_STORE_PRODUCT_STOREID = "Store_id";


    public static final String PROVIDER_NAME = "com.iteso.sesion9";
    public static final int PRODUCTS_TECH = 1;
    public static final int PRODUCTS_HOME = 2;
    public static final int PRODUCTS_ELECTRONICS = 3;
}
