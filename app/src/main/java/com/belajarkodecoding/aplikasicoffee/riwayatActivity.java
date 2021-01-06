package com.belajarkodecoding.aplikasicoffee;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class riwayatActivity extends Fragment {


    public riwayatActivity() {
        // Required empty public constructor
    }
    FirebaseRecyclerAdapter<transaksi, ItemViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_riwayat, container, false);
        FirebaseAuth mAuth =  FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference =  database.getReference().child("user").child(user.getUid()).child("transaksi");
        recyclerView = view.findViewById(R.id.daftar_riwayat);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        load_data();


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Riwayat");
    }

    private void load_data()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<transaksi>()
                        .setQuery(databaseReference,transaksi.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<transaksi, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull transaksi model) {
                holder.txtTanggalBeli.setText(model.getDate());
                holder.txtTotalHarga.setText("Rp. "+String.valueOf(model.getHarga()));
                holder.txtKategori.setText(model.getMetode());
            }



            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.list_riwayat,viewGroup,false);
                return new ItemViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
}