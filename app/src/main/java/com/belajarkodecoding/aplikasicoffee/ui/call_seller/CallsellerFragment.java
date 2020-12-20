package com.belajarkodecoding.aplikasicoffee.ui.call_seller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.belajarkodecoding.aplikasicoffee.R;

public class CallsellerFragment extends Fragment {

    private CallsellerViewModel callsellerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        callsellerViewModel =
                ViewModelProviders.of(this).get(CallsellerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_call_seller, container, false);
        final TextView textView = root.findViewById(R.id.text_call_seller);
        callsellerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}