package com.iteso.sesion9.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyProducts.db";
    private static final int DATABASE_VERSION = 1;
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


    private static DatabaseHandler dataBaseHandler;

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DatabaseHandler getInstance(Context context){
        if(dataBaseHandler == null){
            return new DatabaseHandler(context);
        }else{
            return dataBaseHandler;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CITY_TABLE = "CREATE TABLE "+TABLE_CITY+"("
                +KEY_CITY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_CITY_NAME+" TEXT)";
        db.execSQL(CREATE_CITY_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE "+TABLE_CATEGORY+"("
                +KEY_CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_CATEGORY_NAME+" TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_STORE_TABLE = "CREATE TABLE "+TABLE_STORE+"("
                +KEY_STORE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_STORE_NAME+" TEXT,"
                +KEY_STORE_PHONE+" TEXT,"
                +KEY_STORE_CITYID+" INTEGER,"
                +KEY_STORE_THUMBNAIL+" INTEGER,"
                +KEY_STORE_LATITUDE+ " REAL,"
                +KEY_STORE_LONGITUDE+" REAL,"
                +"FOREIGN KEY ("+KEY_STORE_CITYID+") REFERENCES "+TABLE_CITY+"("+KEY_CITY_ID+"))";
        db.execSQL(CREATE_STORE_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE "+TABLE_PRODUCT+"("
                +KEY_PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_PRODUCT_NAME+" TEXT,"
                +KEY_PRODUCT_DESCRIPTION+" TEXT,"
                +KEY_PRODUCT_IMAGE+ " INTEGER,"
                +KEY_PRODUCT_CATEGORYID+" INTEGER,"
                +"FOREIGN KEY("+KEY_PRODUCT_CATEGORYID+") REFERENCES "+TABLE_CATEGORY+"("+KEY_CATEGORY_ID+"))";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_STORE_PRODUCT_TABLE = "CREATE TABLE "+TABLE_STORE_PRODUCT+"("
                +KEY_STORE_PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_STORE_PRODUCT_PRODUCTID+" INTEGER,"
                +KEY_STORE_PRODUCT_STOREID+" INTEGER,"
                +"FOREIGN KEY("+KEY_STORE_PRODUCT_PRODUCTID+") REFERENCES "+TABLE_PRODUCT+"("+KEY_PRODUCT_ID+"),"
                +"FOREIGN KEY("+KEY_STORE_PRODUCT_STOREID+") REFERENCES "+TABLE_STORE+"("+KEY_STORE_ID+"))";
        db.execSQL(CREATE_STORE_PRODUCT_TABLE);


        db.execSQL("INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('TECHNOLOGY')");


        db.execSQL("INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('HOME')");

        db.execSQL("INSERT INTO "+TABLE_CATEGORY+" ("+KEY_CATEGORY_NAME+")"
                +" VALUES ('ELECTRONICS')");


        db.execSQL("INSERT INTO "+TABLE_CITY+" ("+KEY_CITY_NAME+")"
                +" VALUES ('Guadalajara')");

        db.execSQL("INSERT INTO "+TABLE_CITY+" ("+KEY_CITY_NAME+")"
                +" VALUES ('CDMX')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}