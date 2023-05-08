package com.example.tigo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("¿Que es Tigo Bolivia?\n\n En Bolivia operamos desde 1991. Desde nuestros inicios buscamos dar a nuestros consumidores lo mejor, por eso hoy, 28 años después, estamos orgullosos de ofrecer desde telefonía e Internet móvil de alta velocidad, Internet fijo ilimitado, Televisión por subscripción, contenido de entretenimiento hasta servicios de billetera móvil y servicios corporativos.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}