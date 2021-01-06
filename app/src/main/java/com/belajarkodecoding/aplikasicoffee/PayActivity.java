package com.belajarkodecoding.aplikasicoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PayActivity extends AppCompatActivity {
    //Inisiasi Variabel
    TextView textView;
    Button btn_bayar;
    String kategori = "";
    RadioGroup rg_virtual;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        //Menyambungkan variabel dengan activity
        textView = findViewById(R.id.textView);
        rg_virtual = findViewById(R.id.E_Wallet);
        //Mengambil status user
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        //Mengambil alamat database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseKeranjang = database.getReference("user").child(user.getUid()).child("keranjang");
        final DatabaseReference databaseTransaksi = database.getReference("user").child(user.getUid()).child("transaksi");
    //If else dari radio group
        if (rg_virtual != null){
            rg_virtual.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.gopay:
                            kategori = "Gopay";
                            break;
                        case R.id.ovo:
                            kategori = "Ovo";
                            break;
                        case R.id.dana:
                            kategori = "Dana";
                            break;
                        case R.id.tunai:
                            kategori = "Tunai";
                            break;
                    }
                }
            });
        }


        //Event listener value untuk database
        databaseKeranjang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total = 0;
                int totHarga = 0;
                for(DataSnapshot ds:snapshot.getChildren()){
                Item_keranjang item = ds.getValue(Item_keranjang.class);
                  //menjumlah harga dari transaksi
                    total = Integer.valueOf(item.getTotal());
                    totHarga = totHarga + total;

                }
                //memunculkan hasil hitung jumlah ke textview
                textView.setText(String.valueOf(totHarga));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Menyambungkan variabel dengan activity
        btn_bayar = findViewById(R.id.btn_bayar);
        //Event click listener jika button ditekan
        btn_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Membuat event jika sudah dikonfirmasi untuk dibayar
                databaseTransaksi.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Mendapatkan data harga dari database
                        int harga = Integer.parseInt(textView.getText().toString());
                        //Mengambil data calender sesuai dengan waktu transaksi
                        calendar =  Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date = simpleDateFormat.format(calendar.getTime());
                        //Menambah data ke database
                        databaseTransaksi.push()
                                .setValue(new transaksi(date,kategori,harga)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {


                            }
                        });
                        //Mendelete data dari keranjang
                        databaseKeranjang.removeValue();
                        Intent intent = new Intent(PayActivity.this, selesaiActivity.class );
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}