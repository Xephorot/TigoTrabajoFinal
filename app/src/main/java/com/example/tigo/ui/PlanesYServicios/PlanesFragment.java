package com.example.tigo.ui.PlanesYServicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tigo.databinding.PlanesYServiciosBinding;

public class PlanesFragment extends Fragment {

    private @NonNull PlanesYServiciosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlanesViewModel planesViewModel =
                new ViewModelProvider(this).get(PlanesViewModel.class);

        binding = PlanesYServiciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}