package com.belajarkodecoding.aplikasicoffee.ui.call_seller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CallsellerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CallsellerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is call the seller fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}