package com.example.tigo.ui.Carro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tigo.Carrito.CarritoAdapter;
import com.example.tigo.Carrito.ItemCarrito;
import com.example.tigo.DataBase.DBplan;
import com.example.tigo.R;

import java.util.ArrayList;
import java.util.Locale;

public class CarritoFragment extends Fragment {

    private DBplan dBplan;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private TextView totalCarrito;
    private Button btnVolver, btnPagar;
    private CarritoAdapter carritoAdapter;
    private ArrayList<ItemCarrito> itemsCarrito;
    private View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.carrito, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dBplan = new DBplan(getContext());
        database = dBplan.getReadableDatabase();

        recyclerView = view.findViewById(R.id.recyclerView_carrito);
        totalCarrito = view.findViewById(R.id.total_carrito);
        btnPagar = view.findViewById(R.id.btn_pagar);

        itemsCarrito = obtenerItemsCarrito();
        carritoAdapter = new CarritoAdapter(itemsCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(carritoAdapter);

        actualizarTotalCarrito();



        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPago();
            }
        });
    }

    private ArrayList<ItemCarrito> obtenerItemsCarrito() {
        ArrayList<ItemCarrito> items = new ArrayList<>();
        SQLiteDatabase db = dBplan.getReadableDatabase();

        // Obtener los planes
        // Obtener los planes
        String[] projectionPlan = {
                DBplan.PLAN_COL_ID,
                DBplan.PLAN_COL_NOMBRE,
                DBplan.PLAN_COL_DESCRIPCION,
                DBplan.PLAN_COL_PRECIO,
                "SUM(1) AS cantidad"
        };

        String groupByPlan =   DBplan.PLAN_COL_ID;

        Cursor cursorPlan = db.query(
                DBplan.PLAN_TABLE_NAME,
                projectionPlan,
                null,
                null,
                groupByPlan,
                null,
                null
        );

        while (cursorPlan.moveToNext()) {
            int id = cursorPlan.getInt(cursorPlan.getColumnIndexOrThrow(  DBplan.PLAN_COL_ID));
            String nombre = cursorPlan.getString(cursorPlan.getColumnIndexOrThrow(  DBplan.PLAN_COL_NOMBRE));
            String descripcion = cursorPlan.getString(cursorPlan.getColumnIndexOrThrow(  DBplan.PLAN_COL_DESCRIPCION));
            double precio = cursorPlan.getDouble(cursorPlan.getColumnIndexOrThrow(  DBplan.PLAN_COL_PRECIO));
            int cantidad = cursorPlan.getInt(cursorPlan.getColumnIndexOrThrow("cantidad"));

            ItemCarrito item = new ItemCarrito(id, nombre, descripcion, precio, cantidad);
            items.add(item);
        }
        cursorPlan.close();

        // Obtener los paquetes
        String[] projectionPaquete = {
                DBplan.PAQUETE_COL_ID,
                DBplan.PAQUETE_COL_NOMBRE,
                DBplan.PAQUETE_COL_DESCRIPCION,
                DBplan.PAQUETE_COL_PRECIO,
                "SUM(1) AS cantidad"
        };

        String groupByPaquete =   DBplan.PAQUETE_COL_ID;

        Cursor cursorPaquete = db.query(
                DBplan.PAQUETE_TABLE_NAME,
                projectionPaquete,
                null,
                null,
                groupByPaquete,
                null,
                null
        );

        while (cursorPaquete.moveToNext()) {
            int id = cursorPaquete.getInt(cursorPaquete.getColumnIndexOrThrow(  DBplan.PAQUETE_COL_ID));
            String nombre = cursorPaquete.getString(cursorPaquete.getColumnIndexOrThrow(  DBplan.PAQUETE_COL_NOMBRE));
            String descripcion = cursorPaquete.getString(cursorPaquete.getColumnIndexOrThrow(  DBplan.PAQUETE_COL_DESCRIPCION));
            double precio = cursorPaquete.getDouble(cursorPaquete.getColumnIndexOrThrow(  DBplan.PAQUETE_COL_PRECIO));
            int cantidad = cursorPaquete.getInt(cursorPaquete.getColumnIndexOrThrow("cantidad"));
            ItemCarrito item = new ItemCarrito(id, nombre, descripcion, precio, cantidad);
            items.add(item);
        }
        cursorPaquete.close();

        return items;
    }

    private void actualizarTotalCarrito() {
        double total = 0;
        for (ItemCarrito item : itemsCarrito) {
            total += item.getPrecio() * item.getCantidad();
        }
        totalCarrito.setText(String.format(Locale.getDefault(), "Total: $%.2f", total));
    }

    private void realizarPago() {
        // Aquí debes agregar la lógica para realizar el pago, por ejemplo, procesar el pago
        // con un servicio de terceros y/o guardar los datos del pago en la base de datos.

        // Muestra un mensaje de éxito al finalizar el pago
        Toast.makeText(getActivity(), "El pago fue exitoso", Toast.LENGTH_SHORT).show();
    }
}