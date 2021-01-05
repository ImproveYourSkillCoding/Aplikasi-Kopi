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

    RecyclerView recyclerView;
    Button button;

    public homeActivity() {
        // Required empty public constructor
    }

    FirebaseRecyclerAdapter<Item, ItemViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_home, container, false);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Kopi");

        recyclerView =  view.findViewById(R.id.imageRecyclerView);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        loadData();

        button = (Button) view.findViewById(R.id.keranjang);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KeranjangActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    private void loadData()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(databaseReference,Item.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull final Item model) {

                holder.txtNamaProduk.setText(model.getNama());
                holder.txtHargaProduk.setText("Rp."+model.getHarga());
                Glide.with(getContext())
                        .load(model.getUrl())
                        .into(holder.imgProduk);

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
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_kopi,viewGroup,false);
                return new ItemViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
}