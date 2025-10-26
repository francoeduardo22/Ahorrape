package com.example.ahorra.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem; // <-- AÑADIR IMPORT
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast; // <-- AÑADIR IMPORT

import androidx.annotation.NonNull; // <-- AÑADIR IMPORT
import androidx.annotation.Nullable; // <-- AÑADIR IMPORT
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation; // <-- AÑADIR IMPORT

import com.example.ahorra.R;
import com.google.android.material.appbar.MaterialToolbar; // <-- AÑADIR IMPORT

public class LeccionFragment extends Fragment {

    // (Tu constructor, newInstance, onCreate, etc. irían aquí)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leccion, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Encontrar la Toolbar por su ID
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar_leccion);

        // ========================================================
        // == LÓGICA PARA REGRESAR (LA FLECHA) ==
        // ========================================================
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Simplemente navega "hacia atrás"
                // El Navigation Component sabe que debe mostrar el fragmento anterior
                Navigation.findNavController(v).popBackStack();
            }
        });

        // ========================================================
        // == LÓGICA EXTRA: PARA EL BOTÓN DE GUARDAR (MARCADOR) ==
        // ========================================================
        toolbar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Comprueba qué ítem del menú se presionó
                if (item.getItemId() == R.id.menu_bookmark) {
                    // Lógica para guardar la lección
                    Toast.makeText(getContext(), "Lección Guardada", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}