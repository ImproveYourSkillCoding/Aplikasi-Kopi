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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private Fragment fragment;
    private FragmentTransaction transaction;
    
    private ActionBarDrawerToggle drawerToggle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nvDrawer = findViewById(R.id.nvView);
        mDrawer = findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();

        nvDrawer.setNavigationItemSelectedListener(this);

        nvDrawer.getMenu().getItem(0).setChecked(true);
        firstFragmentDisplay(R.id.nvView);


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        firstFragmentDisplay(item.getItemId());
        return true;
    }

    private void firstFragmentDisplay(int itemId) {

        fragment = null;

        switch (itemId) {
            case R.id.homepage:
                fragment = new homeActivity();
                break;
            case R.id.riwayat:
                fragment = new riwayatActivity();
                break;
            case R.id.kontak_penjual:
                fragment = new kontakActivity();
                break;
            case R.id.logout:
                logout();
                break;

        }

        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flContent, fragment);
            transaction.commit();


        }

        mDrawer.closeDrawers();

    }



    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), SelamatDatangActivity.class));
        finish();

    }
   
}