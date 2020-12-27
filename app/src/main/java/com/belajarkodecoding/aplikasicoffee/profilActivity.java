package com.belajarkodecoding.aplikasicoffee;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class profilActivity extends Fragment implements View.OnClickListener {


    public profilActivity() {
        // Required empty public constructor
    }

    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener authStateListener;
    ImageView imageView;
    TextView textName, textEmail, textPhone;
    FirebaseAuth mAuth;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profil, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        textEmail = (TextView) view.findViewById(R.id.emailProfil);
        textName = (TextView) view.findViewById(R.id.namaProfil);
        textPhone = (TextView) view.findViewById(R.id.nomorProfil);

        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(imageView);

        textName.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());
        textPhone.setText(user.getPhoneNumber());

        btn = (Button)view.findViewById(R.id.btn_logout);
        btn.setOnClickListener(this);
        return view;
    }


    /**    FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), SelamatDatangActivity.class));
        getActivity().finish();
    **/


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                Intent intent = new Intent(v.getContext(), SelamatDatangActivity.class);
                FirebaseAuth.getInstance().signOut();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
        }
    }
}