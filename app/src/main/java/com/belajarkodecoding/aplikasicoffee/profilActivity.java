package com.belajarkodecoding.aplikasicoffee;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class profilActivity extends Fragment implements View.OnClickListener {


    public profilActivity() {
        // Required empty public constructor
    }

    //Deklarasi Variabel
    ImageView imageView;
    TextView textName, textEmail, textPhone, textAlamat;
    FirebaseAuth mAuth;
    DatabaseReference getReference;
    Button btn1, btn2;
    ArrayList dataProfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout untuk fragment ini
        View view = inflater.inflate(R.layout.activity_profil, container, false);
        //Mengambil status dari user
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        //Mengambil alamat database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        getReference = database.getReference();

        //Menggabungkan variabel dengan activity
        textEmail = (TextView) view.findViewById(R.id.emailProfil);
        textName = (TextView) view.findViewById(R.id.namaProfil);
        textPhone = (TextView) view.findViewById(R.id.nomorProfil);
        textAlamat =  (TextView) view.findViewById(R.id.alamatProfil);
        imageView = (ImageView) view.findViewById(R.id.imageProfil);
        //Memasukan gambar user ke dalam imageView
        Glide.with(view)
                .load(user.getPhotoUrl())
                .into(imageView);
        //Menambahkan event listener untuk database
        getReference.child("user").child(user.getUid()).child("profil").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Menaruh data ke textView dan imageview dari database Firebase
                user user = snapshot.getValue(user.class);
                textName.setText(user.getNama());
                textAlamat.setText(user.getAlamat());
                textPhone.setText(user.getNo_telp());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Menuliskan email user pada TextView email
        textEmail.setText(user.getEmail());


        //Menyambungkan Button dengan activity
        btn1 = (Button)view.findViewById(R.id.btn_logout);
        btn2 = (Button)view.findViewById(R.id.btn_edit);
        //Menyambungkan button dengan event click
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                //Event ketika button logout ditekan
                Intent intent1 = new Intent(v.getContext(), SelamatDatangActivity.class);
                FirebaseAuth.getInstance().signOut();
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                getActivity().finish();
                break;
            case R.id.btn_edit:
                //Event ketika button edit ditekan
                Intent intent2 = new Intent(getActivity(), editProfilActivity.class);
                startActivity(intent2);
                break;

        }
    }
}