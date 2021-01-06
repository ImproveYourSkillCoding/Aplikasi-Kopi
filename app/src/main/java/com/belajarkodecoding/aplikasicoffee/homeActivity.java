package com.belajarkodecoding.aplikasicoffee;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.belajarkodecoding.aplikasicoffee.Item;
import com.belajarkodecoding.aplikasicoffee.ItemViewHolder;

import java.net.HttpURLConnection;

public class homeActivity extends Fragment {

    //Deklarasi variabel
    RecyclerView recyclerView;
    Button button;
    FirebaseRecyclerAdapter<Item, ItemViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public homeActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Menyambungkan antara class yang sebagai fragment dengan activity
        View view=inflater.inflate(R.layout.activity_home, container, false);

        //Membuat variabel alamat database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Kopi");
        //Menyambungkan RecycleView dengan activity dan membuat layout grid
        recyclerView =  view.findViewById(R.id.imageRecyclerView);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        //Inisiasi fungsi loadData()
        loadData();

        //Menyambungkan Button dari activity
        button = (Button) view.findViewById(R.id.keranjang);

        //Setting Button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Melakukan setting event untuk mengganti activity ke KeranjangActivity
                Intent intent = new Intent(getContext(), KeranjangActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    private void loadData()
    {
        //Pembuatan Konten yang ada dalam list
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(databaseReference,Item.class)//Pengambilan data dari Firebase
                        .build();//membuat query hasil mengambil dari Firebase

        //Membuat adapter untuk memasukan ke container yang sesuai dengan Item
        recyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull final Item model) {
                //Memasukan nilai kedalam txt dan imageview dengan data dari Firebase
                holder.txtNamaProduk.setText(model.getNama());
                holder.txtHargaProduk.setText("Rp."+model.getHarga());
                Glide.with(getContext())
                        .load(model.getUrl())
                        .into(holder.imgProduk);
                //Membuat cardView Listener untuk pindah ke activity DetailItem
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity)v.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new DetailItem(model.getNama(),model.getHarga(),model.getUrl())).addToBackStack(null).commit();

                    }
                });

            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //Membuat Cardview muncul di activity
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_kopi,viewGroup,false);
                return new ItemViewHolder(view);
            }
        };
        //Membuat adapter untuk mengetahui adanya data yang berubah
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        //Mengeset adapter untuk RecycleView
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}