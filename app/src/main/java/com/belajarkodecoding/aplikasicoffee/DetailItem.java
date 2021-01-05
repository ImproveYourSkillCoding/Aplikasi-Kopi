package com.belajarkodecoding.aplikasicoffee;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DetailItem extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String nama , url;
    int harga;
    Button button;
    ElegantNumberButton Ebutton;

    public DetailItem() {

    }

    public DetailItem(String nama, int harga, String url) {
        this.nama=nama;
        this.harga=harga;
        this.url=url;

    }

    public static DetailItem newInstance(String param1, String param2) {
        DetailItem fragment = new DetailItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail, container, false);
        // deklarasi variable detail
        ImageView itemphoto = view.findViewById(R.id.item_photo_detail);
        TextView judulItem = view.findViewById(R.id.judul);
        TextView hargaItem = view.findViewById(R.id.harga);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        judulItem.setText(nama);
        hargaItem.setText("Rp."+harga);
        Glide.with(getContext()).load(url).into(itemphoto);

        Ebutton = view.findViewById(R.id.btn_jumlah);
        button = view.findViewById(R.id.tambah_keranjang);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String jumlah_buah=Ebutton.getNumber();
                int jumlah = Integer.parseInt(jumlah_buah);
                database.getReference().child("user").child(user.getUid()).child("keranjang").push()
                        .setValue(new Item_keranjang(harga,jumlah,nama,url)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity().getBaseContext(),"Kopi sudah dimasukan kedalam keranjang", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return view;
    }



    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent,
                new homeActivity()).addToBackStack(null).commit();
    }
}