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

import com.example.ahorra.R;
import com.example.ahorra.clases.Menu;
import com.example.ahorra.fragmentos.AprenderFragment;
import com.example.ahorra.fragmentos.HistorialFragment;
import com.example.ahorra.fragmentos.MenuFragment;
import com.example.ahorra.fragmentos.MetasFragment;
import com.example.ahorra.fragmentos.Pantalla_PrincipalFragment;

public class PrincipalActivity extends AppCompatActivity implements Menu {
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
    }

    @Override
    public void onClickMenu(int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.relContendor, fragments[id]);
        ft.commit();
    }
}