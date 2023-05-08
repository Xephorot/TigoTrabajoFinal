package com.example.tigo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private ArrayList<ItemCarrito> itemsCarrito;

    public CarritoAdapter(ArrayList<ItemCarrito> itemsCarrito) {
        this.itemsCarrito = itemsCarrito;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemCarrito currentItem = itemsCarrito.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return itemsCarrito.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreItem;
        private TextView descripcionItem;
        private TextView precioItem;
        private TextView cantidadItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreItem = itemView.findViewById(R.id.nombre_item);
            descripcionItem = itemView.findViewById(R.id.descripcion_item);
            precioItem = itemView.findViewById(R.id.precio_item);
            cantidadItem = itemView.findViewById(R.id.cantidad_item);
        }

        public void bind(ItemCarrito itemCarrito) {
            nombreItem.setText(itemCarrito.getNombre());
            descripcionItem.setText(itemCarrito.getDescripcion());
            precioItem.setText(String.format("Precio: $%.2f", itemCarrito.getPrecio()));
            cantidadItem.setText(String.format("Cantidad: %d", itemCarrito.getCantidad()));
        }
    }
}
