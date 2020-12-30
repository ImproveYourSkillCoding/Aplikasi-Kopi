package com.belajarkodecoding.aplikasicoffee;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailItem extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String judul, harga, purl;

    public DetailItem() {

    }

    public DetailItem(String judul, String harga, String purl) {
        this.judul=judul;
        this.harga=harga;
        this.purl=purl;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_item, container, false);
        ImageView itemphoto = view.findViewById(R.id.item_photo);
        TextView judulItem = view.findViewById(R.id.judul);
        TextView hargaItem = view.findViewById(R.id.harga);

        judulItem.setText(judul);
        hargaItem.setText(harga);
        Glide.with(getContext()).load(purl).into(itemphoto);

        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.home,
                new homeActivity()).addToBackStack(null).commit();
    }
}