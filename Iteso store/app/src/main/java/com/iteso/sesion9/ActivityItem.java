package com.iteso.sesion9;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.database.CategoryControl;
import com.iteso.sesion9.database.DatabaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;
import java.util.List;

public class ActivityItem extends AppCompatActivity {
    Spinner imageSp;
    EditText Text;
    Spinner categoriasSp;
    Spinner tiendasSp;
    Button guardar;
    ArrayList<Category> categories;
    ArrayList<Store> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        imageSp = findViewById(R.id.activity_item_imagenes);
        Text = findViewById(R.id.activity_item_nombre);
        categoriasSp = findViewById(R.id.activity_item_cat);
        tiendasSp = findViewById(R.id.activity_item_store);
        guardar = findViewById(R.id.activity_item_save);

        DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());
        categories = CategoryControl.getCategories(dh);
        List<String> categoryNames = new ArrayList<String>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNames);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriasSp.setAdapter(categoryAdapter);

        stores = StoreControl.getStores(dh);
        List<String> storeNames = new ArrayList<String>();
        for (Store store : stores) {
            storeNames.add(store.getName());
        }
        ArrayAdapter<String> storeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, storeNames);
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiendasSp.setAdapter(storeAdapter);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idCategory = 0;
                int idStore = 0;
                for (Category category : categories) {
                    if (category.getName() == categoriasSp.getSelectedItem().toString()) {
                        idCategory = category.getId();
                    }
                }
                for (Store store : stores) {
                    if (store.getName() == tiendasSp.getSelectedItem().toString()) {
                        idStore = store.getId();
                    }
                }
                ItemProduct product = new ItemProduct();
                product.setTitle(Text.getText().toString());
                product.setImage(imageSp.getSelectedItemPosition());
                Category category = new Category();
                category.setId(idCategory);
                product.setCategory(category);
                Store store = new Store();
                store.setId(idStore);
                product.setStore(store);
                DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());
                ItemProductControl.addItemProduct(product, dh);
                finish();

            }
        });
    }
}
