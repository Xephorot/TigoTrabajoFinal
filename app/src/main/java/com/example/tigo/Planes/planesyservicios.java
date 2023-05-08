package com.example.tigo.Planes;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tigo.DataBase.DBplan;
import com.example.tigo.R;

public class planesyservicios extends AppCompatActivity implements PlanesServiciosContract.View {
    private Button planHogar1Button;
    private Button planHogar2Button;
    private Button planHogar3Button;

    private Button primeButton;
    private Button hboButton;
    private Button adultContentButton;
    private Button resetButton;

    private DBplan dBplan;
    private SQLiteDatabase db;

    private PlanesServiciosContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planes_y_servicios);

        dBplan = new DBplan(this);
        db = dBplan.getWritableDatabase();

        planHogar1Button = findViewById(R.id.planHogar1Button);
        planHogar2Button = findViewById(R.id.planHogar2Button);
        planHogar3Button = findViewById(R.id.planHogar3Button);
        primeButton = findViewById(R.id.button4);
        hboButton = findViewById(R.id.button5);
        adultContentButton = findViewById(R.id.button9);
        resetButton = findViewById(R.id.button6);

        presenter = new PlanesServiciosPresenter(this, dBplan);




        planHogar1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plan planHogar1 = new Plan("Plan Hogar basico", "Internet de 50 mbps, 171 canales", 309.00);
                presenter.onPlanSelected(planHogar1);
                planHogar1Button.setEnabled(false);
                planHogar2Button.setEnabled(false);
                planHogar3Button.setEnabled(false);
            }
        });

        planHogar2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plan planHogar2 = new Plan("Plan Hogar intermedio", "Internet de 80 mbps, 213 canales", 369.00);
                presenter.onPlanSelected(planHogar2);
                planHogar1Button.setEnabled(false);
                planHogar2Button.setEnabled(false);
                planHogar3Button.setEnabled(false);
            }
        });

        planHogar3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plan planHogar3 = new Plan("Plan Hogar avanzado", "Internet de 120 mbps, 269 canales.", 469.00);
                presenter.onPlanSelected(planHogar3);
                planHogar1Button.setEnabled(false);
                planHogar2Button.setEnabled(false);
                planHogar3Button.setEnabled(false);
            }
        });

        primeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primeButton.setEnabled(false);
                ContentValues values = new ContentValues();
                values.put(DBplan.PAQUETE_COL_NOMBRE, "Prime");
                values.put(DBplan.PAQUETE_COL_DESCRIPCION, "Contenido premium de Prime");
                values.put(DBplan.PAQUETE_COL_PRECIO, 50.00);

                long result = db.insert(DBplan.PAQUETE_TABLE_NAME, null, values);

                if (result != -1) {
                    Toast.makeText(planesyservicios.this, "Paquete Prime agregado a tu compra.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(planesyservicios.this, "Error al agregar paquete Prime a tu compra.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hboButton.setEnabled(false);
                ContentValues values = new ContentValues();
                values.put(DBplan.PAQUETE_COL_NOMBRE, "HBO");
                values.put(DBplan.PAQUETE_COL_DESCRIPCION, "Contenido premium de HBO");
                values.put(DBplan.PAQUETE_COL_PRECIO, 75.00);

                long result = db.insert(DBplan.PAQUETE_TABLE_NAME, null, values);

                if (result != -1) {
                    Toast.makeText(planesyservicios.this, "Paquete HBO agregado a tu compra.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(planesyservicios.this, "Error al agregar paquete HBO a tu compra.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        adultContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adultContentButton.setEnabled(false);
                ContentValues values = new ContentValues();
                values.put(DBplan.PAQUETE_COL_NOMBRE, "Contenido para adultos");
                values.put(DBplan.PAQUETE_COL_DESCRIPCION, "Contenido premium para adultos");
                values.put(DBplan.PAQUETE_COL_PRECIO, 100.00);

                long result = db.insert(DBplan.PAQUETE_TABLE_NAME, null, values);

                if (result != -1) {
                    Toast.makeText(planesyservicios.this, "Paquete de contenido para adultos agregado a tu compra.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(planesyservicios.this, "Error al agregar paquete de contenido para adultos a tu compra.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onResetClicked();
            }
        });
    }



    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setButtonEnabled(int buttonId, boolean enabled) {
        findViewById(buttonId).setEnabled(enabled);
    }

    public void resetSelections() {
        planHogar1Button.setEnabled(true);
        planHogar2Button.setEnabled(true);
        planHogar3Button.setEnabled(true);
        primeButton.setEnabled(true);
        hboButton.setEnabled(true);
        adultContentButton.setEnabled(true);

        db.delete(DBplan.PLAN_TABLE_NAME, null, null);
        db.delete(DBplan.PAQUETE_TABLE_NAME, null, null);

        Toast.makeText(this, "Has restablecido tu compra, puedes elegir nuevamente.", Toast.LENGTH_SHORT).show();
        // Restablece las selecciones de los planes y contenidos premium
    }
}

