package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.database.DatabaseHandler;
import com.iteso.sesion9.database.StoreControl;
import com.iteso.sesion9.tools.Constant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {


    public static final String MY_PREFERENCES="com.iteso.sesion9.preferences";

    public static final boolean resetStores=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TimerTask timert = new TimerTask() {
            @Override
            public void run() {


                DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());

                if(resetStores==true)
                StoreControl.deleteData(dh);

                ArrayList<Store> stores = StoreControl.getStores(dh);


                if(stores.size()==0){

                    Store store = new Store();
                    store.setName("BestBuy P V");
                    store.setPhone("3396969");
                    store.setThumbnail(1);
                    store.setLongitude(35253);
                    store.setLatitude(25452);
                    City city = new City();
                    city.setId(2);
                    store.setCity(city);
                    StoreControl.addStore(store, dh);

                    Store store2 = new Store();
                    store2.setName("BestBuy 2");
                    store2.setPhone("335433452");
                    store2.setThumbnail(1);
                    store2.setLongitude(342352);
                    store2.setLatitude(2212.12121);
                    City city2 = new City();
                    city2.setId(1);
                    store2.setCity(city);
                    StoreControl.addStore(store2, dh);

                    Store store3 = new Store();
                    store3.setName("BestBuy 3");
                    store3.setPhone("3332523");
                    store3.setThumbnail(1);
                    store3.setLongitude(5452);
                    store3.setLatitude(545322);
                    City city3 = new City();
                    city3.setId(1);
                    store3.setCity(city);
                    StoreControl.addStore(store3, dh);
                }

                User user = loadPreferences();
                if(user.isLogged()){
                    Intent intent = new Intent(ActivitySplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timert, 2000);
    }
    public User loadPreferences(){
        SharedPreferences sp = getSharedPreferences("com.iteso.PREFERENCES", MODE_PRIVATE);
        User user = new User();
        user.setName(sp.getString("name","UNKNOWN"));
        user.setPassword(sp.getString("password", "123456"));
        user.setLooged(sp.getBoolean("logged", false));
        return user;
    }
}