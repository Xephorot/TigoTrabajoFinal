package com.example.tigo.ui.Carro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarritoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CarritoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Carrito fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}