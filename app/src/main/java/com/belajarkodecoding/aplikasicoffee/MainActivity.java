package com.belajarkodecoding.aplikasicoffee;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    //Deklarasi Variabel
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private BottomNavigationView nvDrawer;
    private Fragment fragment;
    private FragmentTransaction transaction;
    
    private ActionBarDrawerToggle drawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Menyambungkan variabel dengan activity
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nvDrawer = findViewById(R.id.bot_nvView);
        //Mengeset Navigasi ketika bottom navmenu ditekan
        nvDrawer.setOnNavigationItemSelectedListener(this);
        //membuat menu menjadi di home sebagai awal
        nvDrawer.getMenu().getItem(0).setChecked(true);
        //inisiasi fungsi display
        firstFragmentDisplay(R.id.bot_nvView);


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        firstFragmentDisplay(item.getItemId());
        return true;
    }
//fungsi untuk mengganti flcontent
    private void firstFragmentDisplay(int itemId) {

        fragment = new homeActivity();

        switch (itemId) {
            case R.id.homepage:
                fragment = new homeActivity();
                break;
            case R.id.history:
                fragment = new riwayatActivity();
                break;
            case R.id.kontak_penjual:
                fragment = new kontakActivity();
                break;
            case R.id.profil:
                fragment =  new profilActivity();
                break;



        }
        //Menukar flcontent
        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment);
            transaction.commit();


        }


    }


//Event ketika back button dipencet
    @Override
    public void onBackPressed() {
        if (fragment != new homeActivity()) {
            nvDrawer.setSelectedItemId(R.id.homepage);
        } else {
            super.onBackPressed();
        }
    }
   
}