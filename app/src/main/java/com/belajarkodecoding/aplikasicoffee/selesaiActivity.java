package com.belajarkodecoding.aplikasicoffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selesaiActivity extends AppCompatActivity {

    // deklarasi button home
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selesai);

        // memanggil id button home
        btn_home = findViewById(R.id.ok);
        // ketika button home terklik, maka akan pindah ke main activity
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selesaiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
