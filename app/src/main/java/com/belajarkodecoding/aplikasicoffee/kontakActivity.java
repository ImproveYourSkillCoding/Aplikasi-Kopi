package com.belajarkodecoding.aplikasicoffee;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class kontakActivity extends Fragment implements View.OnClickListener {

    public kontakActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_kontak, container, false);
        TextView nSeller = (TextView) root.findViewById(R.id.seller_number);
        nSeller.setOnClickListener(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hubungi Penjual");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.seller_number:
                String seller_number = "+6281213139185";
                Intent dial_seller = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + seller_number));
                startActivity(dial_seller);
                break;
        }
    }

}