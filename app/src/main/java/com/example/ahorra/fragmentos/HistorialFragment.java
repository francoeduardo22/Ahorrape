package com.example.ahorra.fragmentos;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager; // <-- AÑADIDO
import androidx.recyclerview.widget.RecyclerView; // <-- AÑADIDO

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahorra.actividades.HistorialAdapter; // <-- AÑADIDO
import com.example.ahorra.R;
import com.example.ahorra.actividades.Transaccion; // <-- AÑADIDO

// IMPORTS PARA EL CALENDARIO Y LISTAS
import com.google.android.material.datepicker.MaterialDatePicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList; // <-- AÑADIDO
import java.util.Date;
import java.util.List; // <-- AÑADIDO
import java.util.Locale;

public class HistorialFragment extends Fragment {

    // ... (el resto de tu código mParam1, mParam2, etc. va aquí) ...

    // Declarar las variables del RecyclerView y Adapter (AÑADIDO)
    private RecyclerView historialRecyclerView;
    private HistorialAdapter historialAdapter;
    private List<Object> listaAgrupada;


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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- 1. CONFIGURACIÓN DE FILTROS ---

        // Encontrar los TextViews por su ID
        TextView filtroMes = view.findViewById(R.id.filtro_mes);
        TextView filtroCategoria = view.findViewById(R.id.filtro_categoria);
        TextView filtroTipo = view.findViewById(R.id.filtro_tipo);

        // Listener para "Mes"
        filtroMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Seleccionar Mes y Año");
                builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

                MaterialDatePicker<Long> datePicker = builder.build();

                datePicker.addOnPositiveButtonClickListener(selection -> {
                    Date date = new Date(selection);
                    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
                    String fechaFormateada = sdf.format(date);
                    filtroMes.setText(fechaFormateada);
                    // TODO: Aquí irá tu lógica para filtrar la lista por esa fecha
                });
                datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");
            }
        });

        // Listener para "Categoría"
        filtroCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] categorias = {"Todas", "Comida", "Transporte", "Salario", "Compras", "Entretenimiento"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Selecciona una Categoría");
                builder.setItems(categorias, (dialog, which) -> {
                    String categoriaSeleccionada = categorias[which];
                    filtroCategoria.setText(categoriaSeleccionada);
                    // TODO: Aquí irá tu lógica para filtrar la lista por esa categoría
                });
                builder.create().show();
            }
        });

        // Listener para "Tipo"
        filtroTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), filtroTipo);
                popupMenu.getMenu().add("Todos");
                popupMenu.getMenu().add("Ingreso");
                popupMenu.getMenu().add("Gasto");

                popupMenu.setOnMenuItemClickListener(item -> {
                    String tipoSeleccionado = item.getTitle().toString();
                    filtroTipo.setText(tipoSeleccionado);
                    //TODO: Aquí irá tu lógica para filtrar por Ingreso o Gasto
                    return true;
                });
                popupMenu.show();
            }
        });


        // --- 2. CONFIGURACIÓN DEL RECYCLERVIEW (AÑADIDO) ---

        // 1. Encontrar el RecyclerView del XML
        historialRecyclerView = view.findViewById(R.id.historial_recyclerview);
        historialRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 2. Cargar los datos y agruparlos
        cargarDatosDeEjemplo();

        // 3. Crear y conectar el Adapter
        historialAdapter = new HistorialAdapter(getContext(), listaAgrupada);
        historialRecyclerView.setAdapter(historialAdapter);
    }


    // =======================================================
    // MÉTODO DE DATOS DE EJEMPLO (AÑADIDO)
    // =======================================================
    // EN EL FUTURO, ESTOS DATOS VENDRÁN DE TU BASE DE DATOS (ROOM).
    private void cargarDatosDeEjemplo() {
        listaAgrupada = new ArrayList<>();

        // Simulamos la lista que vendría de la base de datos
        // (En la vida real, vendrían ya ordenados por fecha)
        // (Asegúrate de tener los drawables como R.drawable.ic_comida, etc.)
        List<Transaccion> transaccionesDB = new ArrayList<>();
        transaccionesDB.add(new Transaccion("Comida", "10:30 AM", 25.00, "Gasto", R.drawable.ic_comida, new Date(1715781000000L))); // 15 de Mayo 2024
        transaccionesDB.add(new Transaccion("Transporte", "12:45 PM", 5.00, "Gasto", R.drawable.ic_transporte, new Date(1715781000000L))); // 15 de Mayo 2024
        transaccionesDB.add(new Transaccion("Salario", "2:15 PM", 1500.00, "Ingreso", R.drawable.ic_salario, new Date(1715781000000L))); // 15 de Mayo 2024

        transaccionesDB.add(new Transaccion("Compras", "9:00 AM", 120.00, "Gasto", R.drawable.ic_compras, new Date(1715694600000L))); // 14 de Mayo 2024
        transaccionesDB.add(new Transaccion("Entretenimiento", "11:00 AM", 40.00, "Gasto", R.drawable.ic_entretenimiento, new Date(1715694600000L))); // 14 de Mayo 2024

        // --- LÓGICA DE AGRUPACIÓN POR FECHA ---
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'DE' MMMM", new Locale("es", "ES"));
        String fechaActual = "";

        for (Transaccion t : transaccionesDB) {
            String fechaFormateada = sdf.format(t.getFecha()).toUpperCase();

            // Si la fecha de esta transacción es diferente a la anterior...
            if (!fechaFormateada.equals(fechaActual)) {
                //...añadimos el "molde" de fecha (el String) a la lista.
                listaAgrupada.add(fechaFormateada);
                fechaActual = fechaFormateada; // Actualizamos la "fecha actual"
            }

            // Añadimos el "molde" de transacción (el Objeto) a la lista.
            listaAgrupada.add(t);
        }
    }
}