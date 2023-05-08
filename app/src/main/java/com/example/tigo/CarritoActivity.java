package com.example.tigo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tigo.DataBase.DBplan;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity {

    private DBplan dBplan;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private TextView totalCarrito;
    private Button btnVolver, btnPagar;
    private CarritoAdapter carritoAdapter;
    private ArrayList<ItemCarrito> itemsCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito);

        dBplan = new DBplan(this);
        database = dBplan.getReadableDatabase();

        recyclerView = findViewById(R.id.recyclerView_carrito);
        totalCarrito = findViewById(R.id.total_carrito);
        btnVolver = findViewById(R.id.btn_volver);
        btnPagar = findViewById(R.id.btn_pagar);
        setContentView(R.layout.carrito);

        itemsCarrito = obtenerItemsCarrito();
        carritoAdapter = new CarritoAdapter(itemsCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(carritoAdapter);

        actualizarTotalCarrito();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad y vuelve a la anterior
            }
        });

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPago();
            }
        });
    }

    private ArrayList<ItemCarrito> obtenerItemsCarrito() {
        ArrayList<ItemCarrito> items = new ArrayList<>();
        // Aquí debes agregar la lógica para obtener los items del carrito de la base de datos.
        // Por ejemplo, podrías realizar una consulta SQL y guardar los resultados en un Cursor.
        // Luego, puedes recorrer el Cursor y crear objetos ItemCarrito a partir de los datos.

        return items;
    }

    private void actualizarTotalCarrito() {
        double total = 0;
        for (ItemCarrito item : itemsCarrito) {
            total += item.getPrecio() * item.getCantidad();
        }
        totalCarrito.setText(String.format("Total: $%.2f", total));
    }

    private void realizarPago() {
        // Aquí debes agregar la lógica para realizar el pago, por ejemplo, procesar el pago
        // con un servicio de terceros y/o guardar los datos del pago en la base de datos.

        // Muestra un mensaje de éxito al finalizar el pago
        Toast.makeText(this, "El pago fue exitoso", Toast.LENGTH_SHORT).show();
    }
}

