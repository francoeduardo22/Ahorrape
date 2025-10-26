package com.example.ahorra.fragmentos;

import android.content.DialogInterface; // <-- IMPORT AÑADIDO
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog; // <-- IMPORT AÑADIDO
import androidx.appcompat.widget.PopupMenu; // <-- IMPORT AÑADIDO
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem; // <-- IMPORT AÑADIDO
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahorra.R;
// IMPORTS PARA EL CALENDARIO
import com.google.android.material.datepicker.MaterialDatePicker; // <-- IMPORT AÑADIDO
import java.text.SimpleDateFormat; // <-- IMPORT AÑADIDO
import java.util.Date; // <-- IMPORT AÑADIDO
import java.util.Locale; // <-- IMPORT AÑADIDO

public class HistorialFragment extends Fragment {

    // ... (el resto de tu código mParam1, mParam2, etc. va aquí) ...

    public HistorialFragment() {
        // Required empty public constructor
    }

    // ... (tu método newInstance va aquí) ...

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ... (tu código de getArguments va aquí) ...
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }

    // ⭐ AQUÍ ESTÁ TODA LA LÓGICA DE CLICS ACTUALIZADA ⭐
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Encontrar los TextViews por su ID
        TextView filtroMes = view.findViewById(R.id.filtro_mes);
        TextView filtroCategoria = view.findViewById(R.id.filtro_categoria);
        TextView filtroTipo = view.findViewById(R.id.filtro_tipo);

        // =======================================================
        // 1. Listener para "Mes" (Usando un DatePicker)
        // =======================================================
        filtroMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el constructor del DatePicker
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Seleccionar Mes y Año");
                // Iniciar en el mes actual
                builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

                MaterialDatePicker<Long> datePicker = builder.build();

                // Listener para cuando el usuario presiona "OK"
                datePicker.addOnPositiveButtonClickListener(selection -> {
                    // 'selection' es la fecha en milisegundos (timestamp)
                    Date date = new Date(selection);
                    // Formateamos la fecha a "Julio 2025"
                    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
                    String fechaFormateada = sdf.format(date);

                    // Actualizamos el texto del "botón"
                    filtroMes.setText(fechaFormateada);

                    // TODO: Aquí irá tu lógica para filtrar la lista por esa fecha
                });

                // Mostrar el DatePicker
                datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");
            }
        });

        // =======================================================
        // 2. Listener para "Categoría" (Usando un AlertDialog)
        // =======================================================
        filtroCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lista de categorías (puedes cargarlas desde tu BD)
                final String[] categorias = {"Todas", "Comida", "Transporte", "Salario", "Compras", "Entretenimiento"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Selecciona una Categoría");

                // Asignar la lista al diálogo y un listener
                builder.setItems(categorias, (dialog, which) -> {
                    // 'which' es el índice (posición) del item clickeado
                    String categoriaSeleccionada = categorias[which];

                    // Actualizamos el texto del "botón"
                    filtroCategoria.setText(categoriaSeleccionada);

                    // TODO: Aquí irá tu lógica para filtrar la lista por esa categoría
                });

                // Crear y mostrar el diálogo
                builder.create().show();
            }
        });

        // =======================================================
        // 3. Listener para "Tipo" (Usando un PopupMenu)
        // =======================================================
        filtroTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear la instancia de PopupMenu, anclada al botón 'filtroTipo'
                PopupMenu popupMenu = new PopupMenu(getContext(), filtroTipo);

                // Añadir las opciones al menú
                popupMenu.getMenu().add("Todos");
                popupMenu.getMenu().add("Ingreso");
                popupMenu.getMenu().add("Gasto");

                // Listener para cuando se selecciona un item
                popupMenu.setOnMenuItemClickListener(item -> {
                    String tipoSeleccionado = item.getTitle().toString();

                    // Actualizamos el texto del "botón"
                    filtroTipo.setText(tipoSeleccionado);

                    // TODO: Aquí irá tu lógica para filtrar por Ingreso o Gasto
                    return true;
                });

                // Mostrar el menú
                popupMenu.show();
            }
        });
    }
}