package com.belajarkodecoding.aplikasicoffee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class KeranjangActivity extends AppCompatActivity{

    Button btn_pembayaran, btn_cancel;
    TextView txt_total, txt_jumlah;
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
        btn_pembayaran = findViewById(R.id.btn_pembayaran);
        recview = findViewById(R.id.daftar_keranjang);
        txt_jumlah = findViewById(R.id.txt_jumlah);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("user").child(user.getUid()).child("keranjang");

            layoutManager = new LinearLayoutManager(getApplicationContext());
            recview.setLayoutManager(layoutManager);

            loadDataKeranjang();

        btn_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KeranjangActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });



    }

    private void loadDataKeranjang()
    {


        final FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Item_keranjang>()
                        .setQuery(databaseReference,Item_keranjang.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Item_keranjang, ItemViewHolder>(options) {
            int total, totHarga;

            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull final ItemViewHolder holder, int position, @NonNull final Item_keranjang model) {
              holder.txtNamaProduk_cart.setText(model.getNama());
              holder.txtHargaProduk_cart.setText("Rp."+model.getHarga());
              holder.txtJumlahProduk_cart.setText(model.getJumlah()+" Buah");
              Glide.with(getBaseContext())
                      .load(model.getUrl())
                      .into(holder.imgProduk_cart);


                holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Query cancel = databaseReference.orderByChild("nama").equalTo(model.getNama());
                        cancel.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ss: snapshot.getChildren()){
                                    ss.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                databaseReference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull final DataSnapshot snapshot) {
                      total = 0;
                      totHarga = 0;
                      int barang = 0;
                      int totalBarang = 0;

                      for(DataSnapshot ds:snapshot.getChildren()){

                          Item_keranjang item = ds.getValue(Item_keranjang.class);

                          total = Integer.valueOf(item.getTotal());
                          totHarga = totHarga + total;
                          barang = Integer.valueOf(item.getJumlah());
                          totalBarang = totalBarang + barang;
                      }
                      txt_total.setText("Total Yang Harus Dibayar = "+totHarga);
                      txt_jumlah.setText("Jumlah Kopi = "+totalBarang+" Gelas");



                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });







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