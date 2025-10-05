package com.example.ahorra.actividades;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ahorra.R;
import com.example.ahorra.clases.Menu;
import com.example.ahorra.fragmentos.AprenderFragment;
import com.example.ahorra.fragmentos.HistorialFragment;
import com.example.ahorra.fragmentos.MenuFragment;
import com.example.ahorra.fragmentos.MetasFragment;

public class MetasActivity extends AppCompatActivity implements Menu {
    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_metas);

        // insets (si usas R.id.main como root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // prepara fragments
        fragments = new Fragment[4];
        fragments[0] = new MenuFragment();
        fragments[1] = new MetasFragment();
        fragments[2] = new AprenderFragment();
        fragments[3] = new HistorialFragment();

        // carga fragment inicial según intent (o 0 por defecto)
        int id = getIntent().getIntExtra("id", 0);
        if (id < 0 || id >= fragments.length) id = 0;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragments[id])
                .commit();
    }

    @Override
    public void onClickMenu(int id) {
        if (id < 0 || id >= fragments.length) return;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragments[id])
                .commit();
    }

}