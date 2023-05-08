package com.example.tigo.Planes;

public interface PlanesServiciosContract {

    interface View {
        void showToastMessage(String message);
        void setButtonEnabled(int buttonId, boolean enabled);
        void resetSelections();
    }

    interface Presenter {
        void onPlanSelected(Plan plan);
        void onPaqueteSelected(Premium premium);
        void onResetClicked();
    }
}