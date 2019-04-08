package com.iteso.iteso_client;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.iteso.iteso_client.tools.Constant;

public class MainActivity extends AppCompatActivity {

    private static final int TOTAL_PAGES = 3;
    private FragmentTechnology fragmentTechnology;
    private FragmentHome fragmentHome;
    private FragmentElectronics fragmentElectronics;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabs);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        fab = findViewById(R.id.activity_main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityStore.class);
                startActivityForResult(intent, Constant.INTENT_STORES_NOTIFY);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_refresh){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case Constant.FRAGMENT_TECHNOLOGY:
                    if(fragmentTechnology == null)
                        fragmentTechnology = new FragmentTechnology();
                    return fragmentTechnology;
                case Constant.FRAGMENT_HOME:
                    if(fragmentHome == null)
                        fragmentHome = new FragmentHome();
                    return fragmentHome;
                case Constant.FRAGMENT_ELECTRONICS:
                    if(fragmentElectronics == null)
                        fragmentElectronics = new FragmentElectronics();
                    return fragmentElectronics;
                default:
                    return new FragmentTechnology();
            }
        }

        @Override
        public int getCount() {
            return TOTAL_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case Constant.FRAGMENT_TECHNOLOGY:
                    return "Technology";
                case Constant.FRAGMENT_HOME:
                    return "Home";
                case Constant.FRAGMENT_ELECTRONICS:
                    return "Electronics";
            }
            return null;
        }
    }
}
