package com.iteso.iteso_client;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.iteso_client.beans.City;
import com.iteso.iteso_client.beans.CityResolver;
import com.iteso.iteso_client.beans.ItemProductsResolver;
import com.iteso.iteso_client.beans.Store;
import com.iteso.iteso_client.beans.StoreResolver;

import java.util.ArrayList;

public class ActivityStore extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText latitude;
    private EditText longitude;
    private Spinner cities;
    private Button save;

    private ArrayAdapter<City> citiesAdapter;

    private City citySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        name = findViewById(R.id.activity_store_name);
        phone = findViewById(R.id.activity_store_phone);
        latitude = findViewById(R.id.activity_store_latitude);
        longitude = findViewById(R.id.activity_store_longitude);
        cities = findViewById(R.id.activity_store_city);
        save = findViewById(R.id.activity_store_save);

        citySelected = null;

        ArrayList<City> citiesList = CityResolver.getCities(this);

        citiesAdapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, citiesList);
        cities.setAdapter(citiesAdapter);

        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citySelected = citiesAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(citySelected != null) {
                    Store store = new Store();
                    store.setName(name.getText().toString());
                    store.setPhone(phone.getText().toString());
                    store.setLatitude(Double.parseDouble(latitude.getText().toString()));
                    store.setLongitude(Double.parseDouble(longitude.getText().toString()));
                    store.setThumbnail(R.drawable.bestbuy);
                    store.setCity(citySelected);

                    StoreResolver.addStore(store, ActivityStore.this);
                    Intent intent = new Intent();
                    intent.putExtra("STORE NEW", store);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
}
