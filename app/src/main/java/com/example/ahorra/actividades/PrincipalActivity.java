package com.example.ahorra.actividades;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// --- IMPORTS AÑADIDOS PARA EL FAB ---
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
// --- FIN DE IMPORTS AÑADIDOS ---

import com.example.ahorra.R;
import com.example.ahorra.clases.Menu;
import com.example.ahorra.fragmentos.AprenderFragment;
import com.example.ahorra.fragmentos.HistorialFragment;
import com.example.ahorra.fragmentos.LeccionFragment;
import com.example.ahorra.fragmentos.MenuFragment;
import com.example.ahorra.fragmentos.MetasFragment;
import com.example.ahorra.fragmentos.Pantalla_PrincipalFragment;

// (Tu clase ya implementa Menu y OnAprenderInteractionListener)
public class PrincipalActivity extends AppCompatActivity
        implements Menu, AprenderFragment.OnAprenderInteractionListener {

    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // prepara fragments
        fragments = new Fragment[4];
        fragments[0] = new Pantalla_PrincipalFragment();
        fragments[1] = new MetasFragment();
        fragments[2] = new AprenderFragment();
        fragments[3] = new HistorialFragment();

        int id = getIntent().getIntExtra("id", 0);
        onClickMenu(id);

        // ========================================================
        // == CÓDIGO DEL BOTÓN FLOTANTE (FAB) AÑADIDO ==
        // ========================================================
        final int marginDp = 50; // margin en dp desde borde

        int marginPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginDp, getResources().getDisplayMetrics());


        FloatingActionButton fab = new FloatingActionButton(this);

        // Se genera un ID único para el FAB
        fab.setId(ViewCompat.generateViewId());

        fab.setImageResource(android.R.drawable.ic_input_add); // icono "+" incorporado
        fab.setContentDescription("Abrir registro de gastos");

        // Definir parámetros de layout (para el FrameLayout raíz de la ventana)
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM | Gravity.END
        );
        params.setMargins(marginPx, marginPx, marginPx, marginPx);

        // Añadir el FAB a la vista raíz de la *ventana*
        // (Esto lo pone "encima" de tu layout, incluyendo el frgMenu)
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        rootView.addView(fab, params);

        // Asignar el OnClickListener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FAB", "FAB clickeado: abriendo RegistroGasActivity");

                // CAMBIADO: de BienvenidoActivity.this a PrincipalActivity.this
                Intent intent = new Intent(PrincipalActivity.this, com.example.ahorra.actividades.RegistroGasActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        // ========================================================
        // == FIN DEL CÓDIGO DEL FAB ==
        // ========================================================
    }


    @Override
    public void onClickMenu(int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.relContendor, fragments[id]);
        ft.commit();
    }

    // (Tu método onModulo1Clicked() de la interfaz de AprenderFragment)
    @Override
    public void onModulo1Clicked() {
        LeccionFragment leccionFragment = new LeccionFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.relContendor, leccionFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}