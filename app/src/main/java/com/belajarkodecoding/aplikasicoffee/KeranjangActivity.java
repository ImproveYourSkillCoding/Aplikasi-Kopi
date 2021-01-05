package com.belajarkodecoding.aplikasicoffee;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KeranjangActivity extends AppCompatActivity{

    Button btn_bayar;
    TextView txt_total;
    FirebaseRecyclerAdapter<Item_keranjang, ItemViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recview;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        txt_total = findViewById(R.id.txt_total);
        btn_bayar = findViewById(R.id.btn_bayar);
        recview = findViewById(R.id.daftar_keranjang);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("user").child(user.getUid()).child("keranjang");

        if (databaseReference.getDatabase()==null){
            txt_total.setText("Maaf Anda Belum Memesan Apapun, Silahkan Pesan Terlebih Dahulu!");
        } else {
            layoutManager = new LinearLayoutManager(getApplicationContext());
            recview.setLayoutManager(layoutManager);

            loadDataKeranjang();
        }

    }

    private void loadDataKeranjang()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Item_keranjang>()
                        .setQuery(databaseReference,Item_keranjang.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Item_keranjang, ItemViewHolder>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull Item_keranjang model) {
              holder.txtNamaProduk_cart.setText(model.getNama());
              holder.txtHargaProduk_cart.setText("Rp."+model.getHarga());
              Glide.with(getBaseContext())
                      .load(model.getUrl())
                      .into(holder.imgProduk_cart);

                for (int i = 0; i<position;i++){
                    int jumlah_harga = 0;
                    jumlah_harga = model.getJumlah()*model.getHarga();
                    txt_total.setText("Total Yang Harus Dibayar = "+jumlah_harga);
                }


            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_cart,parent,false);
                return new ItemViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recview.setAdapter(recyclerAdapter);

    }

}