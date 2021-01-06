package com.belajarkodecoding.aplikasicoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelamatDatangActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // deklarasi button
        Button btnDaftar, btnMasuk;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selamat_datang);

        // deklarasi id button
        btnDaftar = findViewById(R.id.daftar);
        btnMasuk = findViewById(R.id.masuk);

        // membuat event klik
        btnDaftar.setOnClickListener(this);
        btnMasuk.setOnClickListener(this);

    }

    // pada saat di klik akan berpindah activity sesuai dengan case nya
    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.daftar:
                    Intent intent = new Intent(this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.masuk:
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
    }
}