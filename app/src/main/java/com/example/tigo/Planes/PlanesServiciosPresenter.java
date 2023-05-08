package com.example.tigo.Planes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.tigo.DataBase.DBplan;
import com.example.tigo.R;

public class PlanesServiciosPresenter implements PlanesServiciosContract.Presenter {
    private PlanesServiciosContract.View view;
    private DBplan dBplan;
    private SQLiteDatabase db;

    public PlanesServiciosPresenter(PlanesServiciosContract.View view, DBplan dBplan) {
        this.view = view;
        this.dBplan = dBplan;
        this.db = dBplan.getWritableDatabase();
    }

    @Override
    public void onPlanSelected(Plan plan) {
        ContentValues values = new ContentValues();
        values.put(DBplan.PLAN_COL_NOMBRE, plan.getNombre());
        values.put(DBplan.PLAN_COL_DESCRIPCION, plan.getDescripcion());
        values.put(DBplan.PLAN_COL_PRECIO, plan.getPrecio());

        long result = db.insert(DBplan.PLAN_TABLE_NAME, null, values);

        if (result != -1) {
            view.showToastMessage("Plan " + plan.getNombre() + " agregado a tu compra.");
        } else {
            view.showToastMessage("Error al agregar plan " + plan.getNombre() + " a tu compra.");
        }

        view.setButtonEnabled(R.id.planHogar1Button, false);
        view.setButtonEnabled(R.id.planHogar2Button, false);
        view.setButtonEnabled(R.id.planHogar3Button, false);
    }

    @Override
    public void onPaqueteSelected(Premium premium) {
        ContentValues values = new ContentValues();
        values.put(DBplan.PAQUETE_COL_NOMBRE, premium.getNombre());
        values.put(DBplan.PAQUETE_COL_DESCRIPCION, premium.getDescripcion());
        values.put(DBplan.PAQUETE_COL_PRECIO, premium.getPrecio());

        long result = db.insert(DBplan.PAQUETE_TABLE_NAME, null, values);

        if (result != -1) {
            view.showToastMessage("Paquete " + premium.getNombre() + " agregado a tu compra.");
        } else {
            view.showToastMessage("Error al agregar paquete " + premium.getNombre() + " a tu compra.");
        }

        // Deshabilita el botón de paquete premium correspondiente
        int buttonId;
        switch (premium.getNombre()) {
            case "Prime":
                buttonId = R.id.button4;
                break;
            case "HBO":
                buttonId = R.id.button5;
                break;
            case "Contenido para adultos":
                buttonId = R.id.button9;
                break;
            default:
                return;
        }
        view.setButtonEnabled(buttonId, false);
    }

    @Override
    public void onResetClicked() {
        db.delete(DBplan.PLAN_TABLE_NAME, null, null);
        db.delete(DBplan.PAQUETE_TABLE_NAME, null, null);

        view.showToastMessage("Has restablecido tu compra, puedes elegir nuevamente.");

        view.setButtonEnabled(R.id.planHogar1Button, true);
        view.setButtonEnabled(R.id.planHogar2Button, true);
        view.setButtonEnabled(R.id.planHogar3Button, true);
        view.setButtonEnabled(R.id.button4, true);
        view.setButtonEnabled(R.id.button5, true);
        view.setButtonEnabled(R.id.button9, true);
    }
}