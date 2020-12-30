package com.belajarkodecoding.aplikasicoffee;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeActivity extends Fragment {

    RecyclerView recview;
    AdapterItem adapterItem;
    public homeActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_home, container, false);

        recview=(RecyclerView)view.findViewById(R.id.imageRecyclerView);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Kopi"), Item.class)
                        .build();

        adapterItem = new AdapterItem(options);
        recview.setAdapter(adapterItem);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterItem.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterItem.stopListening();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
}